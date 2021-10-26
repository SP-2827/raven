package in.handyman.raven.process.doa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PipelineAudit extends AbstractAudit {


    private Long pipelineId;

    private String modeOfExecution;
    private String hostName;
    private String threadName;
    private String pipelineLoadType;
    private String fileContent;
    private String requestBody;
    private String relativePath;

}
