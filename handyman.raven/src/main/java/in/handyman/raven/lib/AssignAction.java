package in.handyman.raven.lib;

import in.handyman.raven.lambda.access.ResourceAccess;
import in.handyman.raven.lambda.action.ActionExecution;
import in.handyman.raven.lambda.action.IActionExecution;
import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import in.handyman.raven.lib.model.Assign;
import in.handyman.raven.util.CommonQueryUtil;
import org.jdbi.v3.core.Jdbi;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.util.List;
import java.util.Map;

/**
 * Auto Generated By Raven
 */
@ActionExecution(
        actionName = "Assign"
)
public class AssignAction implements IActionExecution {

    private final ActionExecutionAudit actionExecutionAudit;
    private final Logger log;
    private final Assign assign;

    private final Marker aMarker;

    public AssignAction(final ActionExecutionAudit actionExecutionAudit, final Logger log, final Object assign) {
        this.assign = (Assign) assign;
        this.actionExecutionAudit = actionExecutionAudit;
        this.log = log;
        this.aMarker = MarkerFactory.getMarker("Assign:" + actionExecutionAudit.getActionName());
    }

    @Override
    public void execute() throws Exception {
        final String dbSrc = assign.getSource();
        log.info(aMarker, " input variables id: {}, name: {}, source-database: {} ", actionExecutionAudit.getActionId(), assign.getName(), dbSrc);
        log.info(aMarker, "Sql input post parameter ingestion \n {}", assign.getValue());
        final Map<String, String> context = actionExecutionAudit.getContext();

        final Jdbi jdbi = ResourceAccess.rdbmsJDBIConn(dbSrc);
        jdbi.useTransaction(handle -> {
            final List<String> formattedQuery = CommonQueryUtil.getFormattedQuery(assign.getValue());
            formattedQuery.forEach(sqlToExecute -> {
                log.info(aMarker, "Execution query sql#{} on db=#{}", sqlToExecute, dbSrc);
                handle.createQuery(sqlToExecute).mapToMap().forEach(stringObjectMap -> stringObjectMap.forEach((s, o) ->{
                    context.put(assign.getName().isEmpty()?s:String.format("%s.%s",assign.getName(),s), String.valueOf(o));
                    log.info("Value "+ o +" has been added for "+s);
                }));
            });
        });

    }

    @Override
    public boolean executeIf() {
        return assign.getCondition();
    }
}
