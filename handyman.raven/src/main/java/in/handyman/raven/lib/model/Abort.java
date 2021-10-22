package in.handyman.raven.lib.model;

import in.handyman.raven.action.ActionContext;
import in.handyman.raven.action.IActionContext;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Auto Generated By Raven
 */
@Data
@EqualsAndHashCode
@NoArgsConstructor
@ActionContext(
        actionName = "Abort"
)
public class Abort implements IActionContext {
    private String name;

    private String value;

    private Boolean condition = true;
}
