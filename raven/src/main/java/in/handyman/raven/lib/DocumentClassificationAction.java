package in.handyman.raven.lib;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import in.handyman.raven.exception.HandymanException;
import in.handyman.raven.lambda.action.ActionExecution;
import in.handyman.raven.lambda.action.IActionExecution;
import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import in.handyman.raven.lib.model.DocumentClassification;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;

import okhttp3.*;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

/**
 * Auto Generated By Raven
 */
@ActionExecution(
        actionName = "DocumentClassification"
)
public class DocumentClassificationAction implements IActionExecution {
  private final ActionExecutionAudit action;

  private final Logger log;

  private final DocumentClassification documentClassification;

  private final Marker aMarker;

  private final ObjectMapper mapper = new ObjectMapper();
  private static final MediaType MediaTypeJSON = MediaType
          .parse("application/json; charset=utf-8");
  private final String URI  ;

  public DocumentClassificationAction(final ActionExecutionAudit action, final Logger log,
                                      final Object documentClassification) {
    this.documentClassification = (DocumentClassification) documentClassification;
    this.action = action;
    this.log = log;
    this.aMarker = MarkerFactory.getMarker(" DocumentClassification:"+this.documentClassification.getName());
    this.URI=action.getContext().get("copro.classification-document.url");
  }

  @Override
  public void execute() throws Exception {
    OkHttpClient httpclient = new OkHttpClient();

    final ObjectNode objectNode = mapper.createObjectNode();

    objectNode.put("inputFilePath",documentClassification.getFilePath());
    objectNode.put("outputDir",documentClassification.getOutputDir());
    objectNode.put("modelFilePath",documentClassification.getModelFilePath());
    objectNode.put("labels",documentClassification.getLabels());


    Request request = new Request.Builder().url(URI)
            .post(RequestBody.create( objectNode.toString(),MediaTypeJSON)).build();
    log.info(aMarker, "The request got it successfully the File Path and outputDir {} {}",documentClassification.getFilePath(),documentClassification.getOutputDir(),documentClassification.getModelFilePath(),documentClassification.getLabels());

    try (Response response = httpclient.newCall(request).execute()) {
      String responseBody = response.body().string();
      String name = documentClassification.getName()+"_response";
      String labelName = documentClassification.getName()+"_label";
      JSONObject jsonResult = new JSONObject(responseBody);
      if (response.isSuccessful()) {
        action.getContext().put(name, responseBody);
        action.getContext().put(labelName, jsonResult.getString("label").toLowerCase());
        log.info(aMarker, "The Successful Response  {} {}", name, responseBody);
      }else {
        log.info(aMarker, "The Failure Response  {} {}", name, responseBody);
      }
    }catch (Exception e){
      log.info(aMarker, "The Exception occurred ",e);
      throw new HandymanException("Failed to execute", e);
    }
  }

  @Override
  public boolean executeIf() throws Exception {
    return documentClassification.getCondition();
  }
}


