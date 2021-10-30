package in.handyman.raven.lib;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.zaxxer.hikari.HikariDataSource;
import in.handyman.raven.lym.action.ActionExecution;
import in.handyman.raven.lym.action.IActionExecution;
import in.handyman.raven.audit.AuditService;
import in.handyman.raven.connection.ResourceAccess;
import in.handyman.raven.exception.HandymanException;
import in.handyman.raven.lib.model.Transform;
import in.handyman.raven.util.CommonQueryUtil;
import in.handyman.raven.util.ExceptionUtil;
import in.handyman.raven.util.UniqueID;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.MarkerManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;

/**
 * Auto Generated By Raven
 */
@ActionExecution(
        actionName = "Transform"
)
@Log4j2
public class TransformAction implements IActionExecution {

    private final Context context;
    private final Transform transform;
    private final MarkerManager.Log4jMarker aMarker;

    public TransformAction(final Context context, final Object transform) {
        this.transform = (Transform) transform;
        this.context = context;
        this.aMarker = new MarkerManager.Log4jMarker("Transform");
        this.context.getDetailMap().putPOJO("context", transform);
    }

    @Override
    public void execute() {
        final String dbSrc = transform.getOn();
        log.info("Transform action input variables id: {}, name: {}, source-database: {} ", context.getLambdaId(), transform.getName(), dbSrc);
        log.info("Sql input post parameter ingestion \n {}", transform.getValue());
        final ObjectNode detailMap = context.getDetailMap();
        final HikariDataSource hikariDataSource = ResourceAccess.rdbmsConn(dbSrc);
        try (final Connection connection = hikariDataSource.getConnection()) {
            connection.setAutoCommit(false);
            for (String givenQuery : transform.getValue()) {
                var sqlList = CommonQueryUtil.getFormattedQuery(givenQuery);
                for (var sqlToExecute : sqlList) {
                    log.info("Transform with id:{}, executing script {}", context.getProcessId(), givenQuery);
                    final Long statementId = UniqueID.getId();
                    AuditService.insertStatementAudit(statementId, context.getLambdaId(),
                            transform.getName(), context.getProcessName());
                    try (final Statement stmt = connection.createStatement()) {
                        var rowCount = stmt.executeUpdate(sqlToExecute);
                        var warnings = ExceptionUtil.completeSQLWarning(stmt.getWarnings());
                        detailMap.put(sqlToExecute + ".count", rowCount);
                        detailMap.put(sqlToExecute + ".stmtCount", stmt.getUpdateCount());
                        detailMap.put(sqlToExecute + ".warnings", warnings);
                        AuditService.updateStatementAudit(statementId, rowCount, 0, sqlToExecute, 1);
                        log.info(aMarker, "Transform id# {}, executed script {} rows returned {}", statementId.toString(), sqlToExecute, rowCount);
                        stmt.clearWarnings();
                    } catch (SQLSyntaxErrorException ex) {
                        log.error(aMarker, "Stopping execution, General Error executing sql for {} with for campaign {}", sqlToExecute, ex);
                        detailMap.put(sqlToExecute + ".exception", ExceptionUtil.toString(ex));
                        throw new HandymanException("Process failed", ex);
                    } catch (SQLException ex) {
                        log.error(aMarker, "Continuing to execute, even though SQL Error executing sql for {} ", sqlToExecute, ex);
                        detailMap.put(sqlToExecute + ".exception", ExceptionUtil.toString(ex));
                    } catch (Throwable ex) {
                        log.error(aMarker, "Stopping execution, General Error executing sql for {} with for campaign {}", sqlToExecute, ex);
                        detailMap.put(sqlToExecute + ".exception", ExceptionUtil.toString(ex));
                        throw new HandymanException("Process failed", ex);
                    }
                }
                connection.commit();
                log.info(aMarker, "Completed Transform id#{}, name#{}, dbSrc#{}, sqlList#{}", context.getProcessId(), transform.getName()
                        , dbSrc, sqlList);
            }
        } catch (SQLException ex) {
            log.error(aMarker, "Stopping execution, Fetching connection failed", ex);
            detailMap.put("connection.exception", ExceptionUtil.toString(ex));
            throw new HandymanException("Process failed", ex);
        }
    }


    @Override
    public boolean executeIf() {
        return transform.getCondition();
    }
}
