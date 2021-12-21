package in.handyman.raven.lambda.action;

import com.fasterxml.jackson.databind.JsonNode;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import in.handyman.raven.compiler.RavenParser;
import in.handyman.raven.exception.HandymanException;
import in.handyman.raven.lambda.doa.ActionExecutionAudit;
import in.handyman.raven.lambda.doa.ResourceConnection;
import in.handyman.raven.lib.model.RestPart;
import in.handyman.raven.lib.model.StartProcess;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.Token;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import javax.lang.model.element.Modifier;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ActionGeneration {

    private static final String CONTEXT = "Context";
    private static final String MAIN_JAVA = "src/main/java/";
    private static final String ACTION_IMPL = "Action";

    public void generate(final List<Class<?>> classes, final String modelTargetPackage, final String executionTargetPackage) {
        final Set<String> actions = Arrays.stream(RavenParser.ActionContext.class.getDeclaredMethods())
                .map(Method::getReturnType).map(Class::getSimpleName)
                .filter(s -> s.contains(CONTEXT))
                .collect(Collectors.toSet());
//        final Set<String> attributes = Arrays.stream(RavenParser.AttributeContext.class.getDeclaredMethods())
//                .map(Method::getReturnType).map(Class::getSimpleName)
//                .filter(s -> s.contains(CONTEXT))
//                .collect(Collectors.toSet());
        final List<JavaFile> javaFiles = new ArrayList<>();
        classes.forEach(context -> {
            final String contextFullName = context.getSimpleName();
            final String name = getName(contextFullName);
            if (actions.contains(contextFullName)) {
                javaFiles.add(actionContext(context, name, modelTargetPackage));
                javaFiles.add(execution(contextFullName, modelTargetPackage, executionTargetPackage));
            }
            //TODO auto creation of attributes POJO
            /*else
            if (attributes.contains(contextFullName)) {
                javaFiles.add(attributeContext(context, name, modelTargetPackage));
            }*/
        });
        javaFiles.forEach(javaFile -> {
            final File file = new File(MAIN_JAVA + javaFile.packageName.replace(".", "/")
                    + "/" + javaFile.typeSpec.name + ".java");
            if (!file.exists()) {
                write(javaFile);
            }
        });
    }

    private String getName(final String s) {
        return s.replace(CONTEXT, "");
    }

    private JavaFile actionContext(final Class<?> contextClass, final String actionName, final String modelTargetPackage) {
        final TypeSpec.Builder builder = generateModel(contextClass, actionName);

        final TypeSpec build = builder
                .addSuperinterface(IActionContext.class)
                .addAnnotation(AnnotationSpec.builder(ActionContext.class)
                        .addMember("actionName", String.format("\"%s\"", actionName)).build())
                .build();
        return JavaFile
                .builder(modelTargetPackage, build)
                .build();

    }

    private JavaFile execution(final String actionContext, final String modelTargetPackage, final String executionTargetPackage) {
        final String execution = getLambdaExecution(actionContext);
        final String actionName = getName(actionContext);
        final String variableName = createVariableName(actionName);
        final ClassName actionAttributeClassName = ClassName.get(modelTargetPackage, actionName);
        final TypeSpec typeSpec = TypeSpec
                .classBuilder(execution)
                .addSuperinterface(IActionExecution.class)
                .addJavadoc("Auto Generated By Raven")
                .addAnnotation(AnnotationSpec.builder(ActionExecution.class)
                        .addMember("actionName", String.format("\"%s\"", actionName)).build())
                .addModifiers(Modifier.PUBLIC)
                .addField(ActionExecutionAudit.class, "action", Modifier.FINAL, Modifier.PRIVATE)
                .addField(Logger.class, "log", Modifier.FINAL, Modifier.PRIVATE)
                .addField(actionAttributeClassName, variableName, Modifier.FINAL, Modifier.PRIVATE)
                .addField(Marker.class, "aMarker", Modifier.FINAL, Modifier.PRIVATE)
                .addMethod(MethodSpec.constructorBuilder()
                        .addModifiers(Modifier.PUBLIC)
                        .addParameter(ActionExecutionAudit.class, "action", Modifier.FINAL)
                        .addParameter(Logger.class, "log", Modifier.FINAL)
                        .addParameter(Object.class, variableName, Modifier.FINAL)
                        .addStatement(String.format("this.%s = ($T) %s", variableName, variableName), actionAttributeClassName)
                        .addStatement("this.action = action")
                        .addStatement("this.log = log")
                        .addStatement(String.format("this.aMarker = $T.getMarker(\" %s:\"+this.%s.getName())", actionName, variableName), MarkerFactory.class)
                        .build())
                .addMethod(MethodSpec
                        .methodBuilder("execute")
                        .addModifiers(Modifier.PUBLIC)
                        .addException(Exception.class)
                        .addAnnotation(Override.class)
                        .build())
                .addMethod(MethodSpec
                        .methodBuilder("executeIf")
                        .addModifiers(Modifier.PUBLIC)
                        .addAnnotation(Override.class)
                        .addException(Exception.class)
                        .returns(boolean.class)
                        .addStatement(String.format("return %s.getCondition()", variableName))
                        .build())
                .build();
        return JavaFile
                .builder(executionTargetPackage, typeSpec)
                .build();
    }

    private void write(final JavaFile javaFile) {
        try {
            javaFile.writeTo(new File(MAIN_JAVA));
        } catch (IOException e) {
            throw new HandymanException(javaFile.typeSpec.name + " failed to write", e);
        }
    }

    private TypeSpec.Builder generateModel(final Class<?> contextClass, final String className) {
        final TypeSpec.Builder builder = TypeSpec
                .classBuilder(className)
                .addJavadoc("Auto Generated By Raven")
                .addAnnotation(Data.class)
                .addAnnotation(EqualsAndHashCode.class)
                .addAnnotation(NoArgsConstructor.class)
                .addAnnotation(AllArgsConstructor.class)
                .addAnnotation(Builder.class)
                .addModifiers(Modifier.PUBLIC);
        addFieldMember(contextClass, builder);
        return builder;
    }

    private String getLambdaExecution(final String s) {
        return s.replace(CONTEXT, ACTION_IMPL);
    }

    private static String createVariableName(final String className) {
        if (className != null) {
            return className.substring(0, 1).toLowerCase() + className.substring(1);
        }
        return null;
    }

    private void addFieldMember(final Class<?> contextClass, final TypeSpec.Builder builder) {
        Arrays.asList(contextClass.getDeclaredFields()).forEach(field -> {
            final Class<?> type = field.getType();
            final String name = field.getName();
            if (type == RavenParser.ExpressionContext.class) {
                builder.addField(FieldSpec.builder(Boolean.class, name, Modifier.PRIVATE).initializer("true").build());
            } else if (type == Token.class) {
                builder.addField(String.class, name, Modifier.PRIVATE);
            } else if (type == RavenParser.ResourceContext.class) {
                builder.addField(ResourceConnection.class, name, Modifier.PRIVATE);
            } else if (type == List.class) {
                final Type actualTypeArgument = ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
                if (actualTypeArgument == Token.class) {
                    builder.addField(ParameterizedTypeName.get(ClassName.get(List.class), TypeName.get(String.class)),
                            name, Modifier.PRIVATE);
                } else if (actualTypeArgument == RavenParser.ActionContext.class) {
                    builder.addField(ParameterizedTypeName.get(ClassName.get(List.class), TypeName.get(RavenParser.ActionContext.class)),
                            name, Modifier.PRIVATE);
                } else if (actualTypeArgument == RavenParser.RestPartContext.class) {
                    builder.addField(ParameterizedTypeName.get(ClassName.get(List.class), TypeName.get(RestPart.class)),
                            name, Modifier.PRIVATE);
                } else if (actualTypeArgument == RavenParser.StartProcessContext.class) {
                    builder.addField(ParameterizedTypeName.get(ClassName.get(List.class), TypeName.get(StartProcess.class)),
                            name, Modifier.PRIVATE);
                }
            } else if (type == RavenParser.JsonContext.class) {
                builder.addField(TypeName.get(JsonNode.class),
                        name, Modifier.PRIVATE);
            }
        });
    }

    private JavaFile attributeContext(final Class<?> contextClass, final String name, final String modelTargetPackage) {
        final TypeSpec.Builder builder = generateModel(contextClass, name);
        final TypeSpec build = builder.build();
        return JavaFile
                .builder(modelTargetPackage, build)
                .build();
    }


}
