package in.handyman.raven.lib;

import in.handyman.raven.exception.AbortException;
import in.handyman.raven.lambda.action.ActionExecution;
import in.handyman.raven.lambda.action.IActionExecution;
import in.handyman.raven.lambda.doa.ActionExecutionAudit;
import in.handyman.raven.lib.model.Abort;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

/**
 * Auto Generated By Raven
 */
@ActionExecution(actionName = AbortAction.ABORT)
public class AbortAction implements IActionExecution {

    protected static final String ABORT = "Abort";

    private final ActionExecutionAudit actionExecutionAudit;
    private final Logger log;
    private final Abort abort;
    private final Marker aMarker;

    public AbortAction(final ActionExecutionAudit actionExecutionAudit, final Logger log, final Object abort) {
        this.abort = (Abort) abort;
        this.actionExecutionAudit = actionExecutionAudit;
        this.log = log;
        this.aMarker = MarkerFactory.getMarker(ABORT);
    }

    @Override
    public void execute() {
        log.info(aMarker, "Abort action id#{}, name#{}, calledProcess#{}, message#{}", actionExecutionAudit.getActionId(), abort.getName(),
                actionExecutionAudit.getActionName(), abort.getValue());
        throw new AbortException(abort.getValue());
    }

    @Override
    public boolean executeIf() {
        final Boolean condition = abort.getCondition();
        log.info(aMarker, "Completed evaluation to execute id#{} actionId#{}, name#{}, expression#{}",
                actionExecutionAudit.getActionId(), actionExecutionAudit.getPipelineId(),
                abort.getName(), condition);
        return condition;
    }
}
