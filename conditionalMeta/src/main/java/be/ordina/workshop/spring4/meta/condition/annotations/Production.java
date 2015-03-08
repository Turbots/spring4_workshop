package be.ordina.workshop.spring4.meta.condition.annotations;


import be.ordina.workshop.spring4.meta.condition.Environmental;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Conditional(Environmental.class)
@ApplicationEnvironment(ApplicationEnvironment.Name.PRODUCTION)
public @interface Production {
}
