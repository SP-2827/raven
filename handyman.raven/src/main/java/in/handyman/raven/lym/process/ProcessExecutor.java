package in.handyman.raven.lym.process;

import in.handyman.raven.lym.action.ActionContext;
import in.handyman.raven.lym.action.ActionExecution;
import in.handyman.raven.lym.action.IActionContext;
import in.handyman.raven.lym.action.IActionExecution;
import in.handyman.raven.exception.HandymanException;
import in.handyman.raven.lym.access.ConfigAccess;
import lombok.extern.log4j.Log4j2;
import org.reflections.Reflections;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Log4j2
public class ProcessExecutor {

    protected static final String CONTEXT = "RavenParserContext";
    protected static final Map<String, ? extends Class<?>> ACTION_CONTEXT_MAP;
    protected static final Map<String, ? extends Class<?>> ACTION_EXECUTION_MAP;

    static {
        try {
            log.info("IActionContext Execution classes loader initialized");
            final Set<String> actionPackageNames = getPackageAction();
            final Set<Class<?>> actionContexts = getActionContextClass(actionPackageNames);
            final Set<Class<?>> actionExecutions = getActionExecutionClass(actionPackageNames);

            if (actionExecutions.isEmpty() || actionContexts.isEmpty() || actionExecutions.size() != actionContexts.size()) {
                throw new HandymanException("Size mismatched or Empty IActionContext initialization");
            }

            ACTION_CONTEXT_MAP = getActionContext(actionContexts);
            ACTION_EXECUTION_MAP = getActionExecution(actionExecutions);
            if (!ACTION_EXECUTION_MAP.keySet().containsAll(ACTION_CONTEXT_MAP.keySet())) {
                throw new HandymanException("Mismatched lambdaName");
            }
            log.info("IActionContext Execution classes successfully loaded");
        } catch (Exception e) {
            throw new HandymanException(e.getMessage(), e);
        }
    }

    private static Set<Class<?>> getActionExecutionClass(final Set<String> packageNames) {
        return packageNames.stream().flatMap(packageName -> {
            try {
                final Reflections reflections = new Reflections(packageName);
                return reflections.getTypesAnnotatedWith(ActionExecution.class).stream();
            } catch (Exception e) {
                log.error("ActionExecution failed for the package name {}", packageName, e);
                return null;
            }
        }).filter(Objects::nonNull).collect(Collectors.toSet());
    }

    private static Map<String, ? extends Class<?>> getActionExecution(final Set<Class<?>> classes) {
        return Map.copyOf(classes.stream()
                .filter(aClass -> {
                    final ActionExecution annotation = aClass.getAnnotation(ActionExecution.class);
                    return IActionExecution.class.isAssignableFrom(aClass) &&
                            Objects.nonNull(annotation) && !annotation.actionName().isEmpty() && !annotation.actionName().isBlank();
                }).collect(Collectors.toMap(aClass -> aClass.getAnnotation(ActionExecution.class).actionName(),
                        aClass -> aClass, (p, q) -> p)));
    }

    private static Set<String> getPackageAction() {
        return ConfigAccess.getPackageAction();
    }

    private static Set<Class<?>> getActionContextClass(final Set<String> packageNames) {
        return packageNames.stream().flatMap(packageName -> {
            try {
                final Reflections reflections = new Reflections(packageName);
                return reflections.getTypesAnnotatedWith(ActionContext.class).stream();
            } catch (Exception e) {
                log.error("RavenParserContext failed for the package name {}", packageName, e);
                return null;
            }
        }).filter(Objects::nonNull).collect(Collectors.toSet());
    }

    private static Map<String, ? extends Class<?>> getActionContext(final Set<Class<?>> classes) {
        return Map.copyOf(classes.stream()
                .filter(aClass -> {
                    final ActionContext annotation = aClass.getAnnotation(ActionContext.class);
                    return IActionContext.class.isAssignableFrom(aClass) &&
                            Objects.nonNull(annotation) && !annotation.actionName().isEmpty() && !annotation.actionName().isBlank();
                }).collect(Collectors.toMap(aClass -> aClass.getAnnotation(ActionContext.class).actionName(),
                        aClass -> aClass, (p, q) -> p)));
    }

}
