package in.handyman.raven.lib;

import in.handyman.raven.lambda.action.ActionExecution;
import in.handyman.raven.lambda.action.IActionExecution;
import in.handyman.raven.lambda.doa.ActionExecutionAudit;
import in.handyman.raven.lib.model.DeleteFileDirectory;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.io.File;

/**
 * Auto Generated By Raven
 */
@ActionExecution(
        actionName = "DeleteFileDirectory"
)
public class DeleteFileDirectoryAction implements IActionExecution {
    private final ActionExecutionAudit actionExecutionAudit;

    private final Logger log;

    private final DeleteFileDirectory deleteFileDirectory;

    private final Marker aMarker;

    public DeleteFileDirectoryAction(final ActionExecutionAudit actionExecutionAudit, final Logger log,
                                     final Object deleteFileDirectory) {
        this.deleteFileDirectory = (DeleteFileDirectory) deleteFileDirectory;
        this.actionExecutionAudit = actionExecutionAudit;
        this.log = log;
        this.aMarker = MarkerFactory.getMarker("DeleteFileDirectory");
    }

    @Override
    public void execute() throws Exception {
        //log.info("File/Folder Delete operation has been started for listed files \n {}",deleteFileDirectory.getPath());
        for (String fileName : deleteFileDirectory.getPath()) {
            File deleteFile = new File(fileName);
            if (deleteFile.isDirectory()) {
                deleteDirectory(deleteFile);
            } else {
                deleteFile.delete();
            }
            //log.info("{} File has been deleted",fileName);
        }
        //log.info("File/Folder Delete operation has been completed");
    }

    boolean deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        return directoryToBeDeleted.delete();
    }

    @Override
    public boolean executeIf() throws Exception {
        return false;
    }
}
