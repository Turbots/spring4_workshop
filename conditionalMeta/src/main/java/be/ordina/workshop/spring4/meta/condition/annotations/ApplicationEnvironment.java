package be.ordina.workshop.spring4.meta.condition.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface ApplicationEnvironment {

    public static enum Name {
        PRODUCTION("PRD"), TEST("");
        private final String name;

        private Name(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    Name value();
}
