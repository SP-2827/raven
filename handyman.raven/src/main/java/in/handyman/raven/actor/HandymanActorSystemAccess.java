package in.handyman.raven.actor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import in.handyman.raven.lambda.access.AuditAccess;
import in.handyman.raven.lambda.doa.Action;
import in.handyman.raven.lambda.doa.Pipeline;
import in.handyman.raven.lambda.doa.Statement;

public class HandymanActorSystemAccess {

    private static final ActorSystem SYSTEM = ActorSystem.create("Handyman-DAO-system");

    public static void insert(final Pipeline pipeline) {
//        final ActorRef actorRef = SYSTEM.actorOf(Props.create(AuditActor.class),
//                pipeline.getPipelineName() + ":" + pipeline.getPipelineId());
//        actorRef.tell(pipeline, ActorRef.noSender());
        AuditAccess.insertPipeline(pipeline);
    }

    public static void insert(final Action action) {
//        final ActorRef actorRef = SYSTEM.actorOf(Props.create(AuditActor.class),
//                action.getActionName() + "#" + action.getActionId());
//        actorRef.tell(action, ActorRef.noSender());
        AuditAccess.insertAction(action);
    }

    public static void insert(final Statement statement) {
//        final ActorRef actorRef = SYSTEM.actorOf(Props.create(AuditActor.class),
//                statement.getStatementId() + "#" + statement.getActionId());
//        actorRef.tell(statement, ActorRef.noSender());
        AuditAccess.insertStatement(statement);
    }

    public static void update(final Pipeline pipeline) {
        AuditAccess.update(pipeline);
    }

    public static void update(final Action action) {
        AuditAccess.update(action);
    }
}
