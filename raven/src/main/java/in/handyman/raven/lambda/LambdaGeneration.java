package in.handyman.raven.lambda;

import com.fasterxml.jackson.databind.JsonNode;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import in.handyman.raven.compiler.RavenParser;
import in.handyman.raven.context.ActionContext;
import in.handyman.raven.exception.HandymanException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.antlr.v4.runtime.Token;
import org.apache.logging.log4j.MarkerManager;

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

public class LambdaGeneration {

    private static final String CONTEXT = "Context";
    private static final String MAIN_JAVA = "src/main/java/";
    private static final String ACTION_IMPL = "Action";

    public void generate(final Set<String> givenActions, final List<Class<?>> classes, final String modelTargetPackage, final String executionTargetPackage) {
        final Set<String> actions = Arrays.stream(RavenParser.ActionContext.class.getDeclaredMethods())
                .map(Method::getReturnType).map(Class::getSimpleName)
                .filter(s -> s.contains(CONTEXT))
                .collect(Collectors.toSet());
        final List<JavaFile> javaFiles = new ArrayList<>();
        classes.forEach(actionContext -> {
            final String actionContextFullName = actionContext.getSimpleName();
            final String lambdaName = getLambdaName(actionContextFullName);
            if (actions.contains(actionContextFullName)) {
                javaFiles.add(actionAttribute(actionContext, lambdaName, modelTargetPackage));
                javaFiles.add(execution(actionContextFullName, modelTargetPackage, executionTargetPackage));
            }
        });
        javaFiles.forEach(javaFile -> {
            final File file = new File(MAIN_JAVA + javaFile.packageName.replace(".", "/")
                    + "/" + javaFile.typeSpec.name + ".java");
            if (!file.exists()) {
                write(javaFile);
            }
        });
    }

    private JavaFile execution(final String actionContext, final String modelTargetPackage, final String executionTargetPackage) {
        final String execution = getLambdaExecution(actionContext);
        final String lambdaName = getLambdaName(actionContext);
        final ClassName actionAttributeClassName = ClassName.get(modelTargetPackage, lambdaName);
        final TypeSpec typeSpec = TypeSpec
                .classBuilder(execution)
                .addSuperinterface(LambdaExecution.class)
                .addJavadoc("Auto Generated By Raven")
                .addAnnotation(AnnotationSpec.builder(LambdaAutowire.class)
                        .addMember("lambdaName", String.format("\"%s\"", lambdaName)).build())
                .addAnnotation(Log4j2.class)
                .addModifiers(Modifier.PUBLIC)
                .addField(ActionContext.class, "actionContext", Modifier.FINAL, Modifier.PRIVATE)
                .addField(actionAttributeClassName, "context", Modifier.FINAL, Modifier.PRIVATE)
                .addField(MarkerManager.Log4jMarker.class, "aMarker", Modifier.FINAL, Modifier.PRIVATE)
                .addMethod(MethodSpec.constructorBuilder()
                        .addModifiers(Modifier.PUBLIC)
                        .addParameter(ActionContext.class, "actionContext", Modifier.FINAL)
                        .addParameter(Object.class, "context", Modifier.FINAL)
                        .addStatement("this.context = ($T) context", actionAttributeClassName)
                        .addStatement("this.actionContext = actionContext")
                        .addStatement(String.format("this.aMarker = new $T(\"%s\")", lambdaName), MarkerManager.Log4jMarker.class)
                        .addStatement("this.actionContext.getDetailMap().putPOJO(\"context\", context)")
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
                        .addStatement("return false")
                        .build())
                .build();
        return JavaFile
                .builder(executionTargetPackage, typeSpec)
                .build();
    }

    private String getLambdaExecution(final String s) {
        return s.replace(CONTEXT, ACTION_IMPL);
    }

    private String getLambdaName(final String s) {
        return s.replace(CONTEXT, "");
    }

    private void write(final JavaFile javaFile) {
        try {
            javaFile.writeTo(new File(MAIN_JAVA));
        } catch (IOException e) {
            throw new HandymanException(javaFile.typeSpec.name + " failed to write", e);
        }
    }

    private JavaFile actionAttribute(final Class<?> lambdaContextClass, final String lambdaName, final String modelTargetPackage) {
        final TypeSpec.Builder builder = TypeSpec
                .classBuilder(lambdaName)
                .addJavadoc("Auto Generated By Raven")
                .addAnnotation(Data.class)
                .addAnnotation(EqualsAndHashCode.class)
                .addAnnotation(NoArgsConstructor.class)
                .addAnnotation(AnnotationSpec.builder(LambdaContext.class)
                        .addMember("lambdaName", String.format("\"%s\"", lambdaName)).build())
                .addModifiers(javax.lang.model.element.Modifier.PUBLIC);
        Arrays.asList(lambdaContextClass.getDeclaredFields()).forEach(field -> {
            final Class<?> type = field.getType();
            final String name = field.getName();
            if (type == RavenParser.ExpressionContext.class) {
                builder.addField(Boolean.class, name, Modifier.PRIVATE);
            } else if (type == Token.class) {
                builder.addField(String.class, name, Modifier.PRIVATE);
            } else if (type == List.class) {
                final Type actualTypeArgument = ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
                if (actualTypeArgument == Token.class) {
                    builder.addField(ParameterizedTypeName.get(ClassName.get(List.class), TypeName.get(String.class)),
                            name, Modifier.PRIVATE);
                } else if (actualTypeArgument == RavenParser.ActionContext.class) {
                    builder.addField(ParameterizedTypeName.get(ClassName.get(List.class), TypeName.get(RavenParser.ActionContext.class)),
                            name, Modifier.PRIVATE);
                }
            } else if (type == RavenParser.JsonContext.class) {
                builder.addField(TypeName.get(JsonNode.class),
                        name, Modifier.PRIVATE);
            }
        });

        final TypeSpec build = builder
                .addSuperinterface(Lambda.class)
                .build();
        return JavaFile
                .builder(modelTargetPackage, build)
                .build();

    }


}
