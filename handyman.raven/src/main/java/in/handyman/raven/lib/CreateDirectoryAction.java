package in.handyman.raven.lib;

import in.handyman.raven.lambda.action.ActionExecution;
import in.handyman.raven.lambda.action.IActionExecution;
import in.handyman.raven.lambda.doa.ActionExecutionAudit;
import in.handyman.raven.lib.model.CreateDirectory;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Auto Generated By Raven
 */
@ActionExecution(
        actionName = "CreateDirectory"
)
public class CreateDirectoryAction implements IActionExecution {
    private final ActionExecutionAudit actionExecutionAudit;

    private final Logger log;

    private final CreateDirectory createDirectory;

    private final Marker aMarker;

    public CreateDirectoryAction(final ActionExecutionAudit actionExecutionAudit, final Logger log,
                                 final Object createDirectory) {
        this.createDirectory = (CreateDirectory) createDirectory;
        this.actionExecutionAudit = actionExecutionAudit;
        this.log = log;
        this.aMarker = MarkerFactory.getMarker("CreateDirectory:" + this.createDirectory.getName());
    }

    @Override
    public void execute() throws Exception {
        log.info(aMarker, "Directory creation operation has been started for listed files {}", createDirectory.getDirectoryPath());
        for (var fileName : createDirectory.getDirectoryPath()) {
            var path = Paths.get(fileName);
            if (!Files.exists(path)) {
                Files.createDirectory(path);
                log.info(aMarker, "{} Directory Created", path);
            } else {
                log.info(aMarker, "{} Directory already exists", path);
            }
        }
    }

    @Override
    public boolean executeIf() throws Exception {
        return createDirectory.getCondition();
    }
}
