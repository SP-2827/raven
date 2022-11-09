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
        actionName = "SorGroupDetails"
)
public class SorGroupDetails implements IActionContext {
    private String name;

    private String on;

    private String searchfields;

    private String keyfields;

    private String groupbyfields;

    private String targettable;

    private String value;

    private Boolean condition = true;
}
