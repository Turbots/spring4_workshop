package be.ordina.workshop.spring4.condition;

import be.ordina.workshop.spring4.condition.annotations.ApplicationEnvironment;
import be.ordina.workshop.spring4.condition.exception.MissingApplicationEnvironmentException;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.core.type.classreading.AnnotationMetadataReadingVisitor;

import java.util.Map;

public class Environmental implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String environment = context.getEnvironment().getProperty("environment", "");

        Map<String, Object> annotationAttributes =
                metadata.getAnnotationAttributes(ApplicationEnvironment.class.getCanonicalName());

        if (annotationAttributes != null) {
            ApplicationEnvironment.Name envName = (ApplicationEnvironment.Name) annotationAttributes.get("value");
            if (envName.getName().equals(environment)) {
                return true;
            }
        }

        throw new MissingApplicationEnvironmentException((AnnotationMetadataReadingVisitor) metadata);
    }
}
