package in.handyman.raven.lib.model;

import in.handyman.raven.lambda.action.ActionContext;
import in.handyman.raven.lambda.action.IActionContext;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Auto Generated By Raven
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ActionContext(
        actionName = "TriageAttribution"
)
public class TriageAttribution implements IActionContext {

    private String name;

    private String outputDir;

    private String labelledClassifierModelFilePath;

    private String handwrittenClassifierModelFilePath;

    private String checkboxClassifierModelFilePath;

    private String synonyms;

    private String labelledClassifierLabels;

    private String viltCocoLabels;

    private String viltConfigLabel;

    private String isViltCocoOverride;

    private String viltCocoThreshold;

    private String inputFilePath;

    private Boolean condition = true;

    private String triageAttributionResponseName;

}
