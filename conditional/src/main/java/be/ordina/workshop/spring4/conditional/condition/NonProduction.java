package be.ordina.workshop.spring4.conditional.condition;

import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class NonProduction extends Production {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return !super.matches(context, metadata);
    }
}
