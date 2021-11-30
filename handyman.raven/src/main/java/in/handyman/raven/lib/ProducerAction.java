package in.handyman.raven.lib;

import com.fasterxml.jackson.databind.ObjectMapper;
import in.handyman.raven.lambda.action.ActionExecution;
import in.handyman.raven.lambda.action.IActionExecution;
import in.handyman.raven.lambda.doa.Action;
import in.handyman.raven.lambda.process.LambdaEngine;
import in.handyman.raven.lib.model.Producer;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.util.Optional;

/**
 * Auto Generated By Raven
 */
@ActionExecution(
        actionName = "Producer"
)
public class ProducerAction implements IActionExecution {

    private final Action action;
    private final Logger log;
    private final Producer producer;
    private final Marker aMarker;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ProducerAction(final Action action, final Logger log, final Object producer) {
        this.producer = (Producer) producer;
        this.action = action;
        this.log = log;
        this.aMarker = MarkerFactory.getMarker(" Producer:" + this.producer.getName());
    }

    @Override
    public void execute() {
        log.info(aMarker, "given {}", producer);

        log.info(aMarker, "Execution has been started in a Parallel");

        Optional.ofNullable(producer.getActions()).filter(x -> !x.isEmpty())
                .ifPresent(actionContexts -> {


                    actionContexts.stream()
                            .map(actionContext -> {
                                var vAction = LambdaEngine.getAction(producer.getName(), action);
                                vAction.setContext(action.getContext());
                                log.info(aMarker, "Before execution Context {}", vAction.getContext());
                                return new ActionCallable(actionContext, vAction, null);
                            })
                            .forEach(ActionCallable::run);

                    log.info(aMarker, "After execution Context {}", action.getContext());

                    final String key = action.getContext().get(producer.getPush());


                    if (key != null) {
                        producer.getNodes().add(key);
                    }

                });

        log.info(aMarker, "Completed Execution");

    }

    public Action getAction() {
        return action;
    }

    public Producer getProducer() {
        return producer;
    }

    @Override
    public boolean executeIf() {
        return producer.getCondition();
    }
}
