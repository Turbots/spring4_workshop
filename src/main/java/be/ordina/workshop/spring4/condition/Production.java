package be.ordina.workshop.spring4.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class Production implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String environment = context.getEnvironment().getProperty("environment", "");
        return environment.equals("PRD");
    }
}
