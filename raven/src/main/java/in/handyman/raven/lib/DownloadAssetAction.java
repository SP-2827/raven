package in.handyman.raven.lib;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import in.handyman.raven.exception.HandymanException;
import in.handyman.raven.lambda.action.ActionExecution;
import in.handyman.raven.lambda.action.IActionExecution;
import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import in.handyman.raven.lib.model.DownloadAsset;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import okhttp3.*;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

/**
 * Auto Generated By Raven
 */
@ActionExecution(
    actionName = "DownloadAsset"
)
public class DownloadAssetAction implements IActionExecution {
  private final ActionExecutionAudit action;

  private final Logger log;

  private final DownloadAsset downloadAsset;
 private final     ObjectMapper mapper = new ObjectMapper();
  private static final MediaType MediaTypeJSON = MediaType
          .parse("application/json; charset=utf-8");
  private  final String URI ;

  private final Marker aMarker;

  public DownloadAssetAction(final ActionExecutionAudit action, final Logger log,
      final Object downloadAsset) {
    this.downloadAsset = (DownloadAsset) downloadAsset;
    this.action = action;
    this.log = log;
    this.aMarker = MarkerFactory.getMarker(" DownloadAsset:"+this.downloadAsset.getName());
    this.URI=action.getContext().get("copro.download-file.url");

  }

  @Override
  public void execute() throws Exception {
    OkHttpClient httpclient = new OkHttpClient();
    // convert the book to JSON by Jackson

    final ObjectNode objectNode = mapper.createObjectNode();

    objectNode.putPOJO("urls", Collections.singletonList(downloadAsset.getUrl()));
    objectNode.put("outputDir",downloadAsset.getLocation());

    // build a request
    Request request = new Request.Builder().url(this.URI)
            .post(RequestBody.create( objectNode.toString(),MediaTypeJSON)).build();
    log.info(aMarker, "The request got it successfully the url and outputDir {} {}",downloadAsset.getUrl(),downloadAsset.getLocation() );

    try (Response response = httpclient.newCall(request).execute()) {
      String responseBody = Objects.requireNonNull(response.body()).string();
      String name = downloadAsset.getName();
      if (response.isSuccessful()) {
          JSONObject json = new JSONObject(responseBody);
          String path = json.get("paperPaths").toString();
        action.getContext().put(name, path);
        log.info(aMarker, "The Successful Response  {} {}", name, responseBody);
      }else {
        log.info(aMarker, "The Response  {} {}", name, responseBody);
          action.getContext().put(name+".errorMessage", responseBody);
      }
        action.getContext().put(name+".isSuccessful", String.valueOf(response.isSuccessful()));
    }catch (Exception e){
      log.info(aMarker, "The Exception occurred ",e);
      throw new HandymanException("Failed to execute", e);
    }

  }

  @Override
  public boolean executeIf() throws Exception {
    return downloadAsset.getCondition();
  }
}
