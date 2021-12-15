package in.handyman.raven.lib;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import in.handyman.raven.exception.HandymanException;
import in.handyman.raven.lambda.action.ActionExecution;
import in.handyman.raven.lambda.action.IActionExecution;
import in.handyman.raven.lambda.doa.Action;
import in.handyman.raven.lambda.doa.ResourceConnection;
import in.handyman.raven.lambda.process.CommandProxy;
import in.handyman.raven.lambda.process.LambdaEngine;
import in.handyman.raven.lib.model.Consumer;
import in.handyman.raven.lib.model.Producer;
import in.handyman.raven.lib.model.ProducerConsumerModel;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * Auto Generated By Raven
 */
@ActionExecution(
        actionName = "ProducerConsumerModel"
)
public class ProducerConsumerModelAction implements IActionExecution {
    private final Action action;

    private final Logger log;

    private final ProducerConsumerModel producerConsumerModel;

    private final Marker aMarker;

    public ProducerConsumerModelAction(final Action action, final Logger log,
                                       final Object producerConsumerModel) {
        this.producerConsumerModel = (ProducerConsumerModel) producerConsumerModel;
        this.action = action;
        this.log = log;
        this.aMarker = MarkerFactory.getMarker(" ProducerConsumerModel:" + this.producerConsumerModel.getName());
    }

    @Override
    public void execute() throws Exception {

        log.info(aMarker, "Given {}", producerConsumerModel);
        final Integer pThreadCount = Optional.ofNullable(producerConsumerModel.getProduceThreadCount()).map(Integer::valueOf).orElse(1);
        final Integer cThreadCount = Optional.ofNullable(producerConsumerModel.getConsumeThreadCount()).map(Integer::valueOf).orElse(1);

        final ThreadFactoryBuilder producerThreadFactoryBuilder = new ThreadFactoryBuilder();
        producerThreadFactoryBuilder.setNameFormat("Producer-%d");
        final ThreadFactoryBuilder consumerThreadFactoryBuilder = new ThreadFactoryBuilder();
        consumerThreadFactoryBuilder.setNameFormat("Consumer-%d");
        final ExecutorService pExecutorService = Executors.newFixedThreadPool(pThreadCount, producerThreadFactoryBuilder.build());
        final ExecutorService cExecutorService = Executors.newFixedThreadPool(cThreadCount, consumerThreadFactoryBuilder.build());

        final Long pipelineId = this.action.getPipelineId();

        this.action.getContext().put("pcmId", String.valueOf(pipelineId));

        final List<ProducerAction> producerActions = producerConsumerModel.getProduce().stream().flatMap(producerContext -> {

            var producer = new Producer();
            producer.setPcmId(pipelineId);
            producer.setSource(producerConsumerModel.getSource());

            CommandProxy.setTarget(producer, producerContext, action.getContext());
            log.info(aMarker, "{}", producerContext);

            final ResourceConnection source = producer.getSource();
            final List<Map<String, String>> maps = source.get()
                    .withHandle(handle -> handle.createQuery(producer.getStmt()).mapToMap().stream()
                            .map(stringObjectMap -> stringObjectMap.entrySet()
                                    .stream().collect(Collectors.toMap(Map.Entry::getKey,
                                            x -> String.valueOf(x.getValue()), (p, q) -> p)))
                            .collect(Collectors.toList()));

            return maps.stream().map(stringObjectMap -> {
                var vAction = LambdaEngine.getAction(producer.getName(), action);

                final Map<String, String> map = new HashMap<>(Map.copyOf(action.getContext()));
                map.putAll(stringObjectMap);
                vAction.setContext(map);

                return new ProducerAction(vAction, log, producer);
            });

        }).collect(Collectors.toList());

        final int size = producerActions.size();
        var countDown = new CountDownLatch(size);
        final String poison = UUID.randomUUID().toString();

        final List<ConsumerAction> consumerActions = producerConsumerModel.getConsume().stream().map(consumerContext -> {
            var consumer = new Consumer();
            consumer.setPoison(poison);
            consumer.setPcmId(pipelineId);
            consumer.setSource(producerConsumerModel.getSource());

            CommandProxy.setTarget(consumer, consumerContext, action.getContext());
            log.info(aMarker, "{}", consumerContext);
            var vAction = LambdaEngine.getAction(consumer.getName(), action);
            return new ConsumerAction(vAction, log, consumer);
        }).collect(Collectors.toList());

        var consumerCountDown = new CountDownLatch(consumerActions.size());

        try {
            consumerActions.forEach(consumerAction -> cExecutorService.submit(() -> {
                try {
                    LambdaEngine.execute(consumerAction, consumerAction.getAction());
                } catch (Throwable e) {
                    throw new HandymanException("Failed to execute", e);
                } finally {
                    consumerCountDown.countDown();
                }

            }));

            producerActions.forEach(producerAction -> pExecutorService.submit(() -> {

                try {
                    LambdaEngine.execute(producerAction, producerAction.getAction());
                } catch (Throwable e) {
                    throw new HandymanException("Failed to execute", e);
                } finally {
                    countDown.countDown();
                }

            }));

            try {
                countDown.await();
            } catch (InterruptedException e) {
                throw new HandymanException("Failed to execute", e);
            }
        } catch (Throwable e) {
            throw new HandymanException("Failed to execute", e);
        } finally {
//            producerConsumerModel.getSource().get()
//                    .useHandle(handle -> handle.createUpdate("INSERT INTO pcm_event ( payload, pcm_id,process) VALUES(:payload, :pcmId,0)")
//                            .bind("payload", poison).bind("pcmId", pipelineId).execute());
            try {
                consumerCountDown.await();
            } catch (InterruptedException e) {
                log.error("Poison addition failed", e);
            }
        }
    }


    @Override
    public boolean executeIf() throws Exception {
        return producerConsumerModel.getCondition();
    }
}
