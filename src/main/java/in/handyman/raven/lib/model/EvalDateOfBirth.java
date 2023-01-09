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
        actionName = "EvalDateOfBirth"
)
public class EvalDateOfBirth implements IActionContext {
    private String name;

    private String dob;

    private int wordCountLimit;

    private int charCountLimit;

    private int wordCountThreshold;

    private int charCountThreshold;

    private int comparableYear;

    private String dateFormats;

    private int validatorThreshold;

    private Boolean condition = true;
}
