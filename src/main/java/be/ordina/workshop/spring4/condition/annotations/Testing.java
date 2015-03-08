package be.ordina.workshop.spring4.condition.annotations;

import be.ordina.workshop.spring4.condition.Environmental;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Conditional(Environmental.class)
@ApplicationEnvironment(ApplicationEnvironment.Name.TEST)
public @interface Testing {
}
