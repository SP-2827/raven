package in.handyman.raven.lib;

import in.handyman.raven.exception.HandymanException;
import in.handyman.raven.lambda.action.ActionExecution;
import in.handyman.raven.lambda.action.IActionExecution;
import in.handyman.raven.lambda.doa.audit.ActionExecutionAudit;
import in.handyman.raven.lib.adapters.CharacterCountAdapter;
import in.handyman.raven.lib.interfaces.AdapterInterface;
import in.handyman.raven.lib.model.Charactercount;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

/**
 * Auto Generated By Raven
 */
@ActionExecution(
        actionName = "Charactercount"
)
public class CharactercountAction implements IActionExecution {
    private final ActionExecutionAudit action;

    private final Logger log;

    private final Charactercount charactercount;

    private final Marker aMarker;

    public CharactercountAction(final ActionExecutionAudit action, final Logger log,
                                final Object charactercount) {
        this.charactercount = (Charactercount) charactercount;
        this.action = action;
        this.log = log;
        this.aMarker = MarkerFactory.getMarker(" Charactercount:" + this.charactercount.getName());
    }

    public static int getCharCount(String input, int countLimit, int threshold) {
        int confidenceScore = 0;
        try {
            AdapterInterface charCountAdapter = new CharacterCountAdapter();
            int wordCount = charCountAdapter.getThresoldScore(input);
            confidenceScore = wordCount <= countLimit ? threshold : 0;
        } catch (Exception ex) {
            throw new HandymanException("Failed to execute char count", ex);
        }
        return confidenceScore;
    }

    @Override
    public void execute() throws Exception {
        try {
            log.info(aMarker, "<-------Character Count Action for {} has been started------->" + charactercount.getName());
            AdapterInterface charCountAdapter = new CharacterCountAdapter();
            int wordCount = charCountAdapter.getThresoldScore(charactercount.getInputValue());
            int confidenceScore = wordCount <= Integer.parseInt(charactercount.getCountLimit())
                    ? Integer.parseInt(charactercount.getThresholdValue()) : 0;
            action.getContext().put(charactercount.getName().concat(".score"), String.valueOf(confidenceScore));
            log.info(aMarker, "<-------Character Count Action for {} has been completed------->" + charactercount.getName());

        } catch (Exception ex) {
            action.getContext().put(charactercount.getName().concat(".error"), "true");
            log.info(aMarker, "The Exception occurred ", ex);
            throw new HandymanException("Failed to execute", ex);
        }
    }

    @Override
    public boolean executeIf() throws Exception {
        return charactercount.getCondition();
    }
}
