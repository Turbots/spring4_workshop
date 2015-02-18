package be.ordina.workshop.spring4.condition.exception;

import org.springframework.core.type.classreading.AnnotationMetadataReadingVisitor;

public class MissingApplicationEnvironmentException extends RuntimeException {

    public MissingApplicationEnvironmentException(AnnotationMetadataReadingVisitor metadata) {
        super(createMessage(metadata));
    }

    private static String createMessage(AnnotationMetadataReadingVisitor metadata) {
        return "Class " + metadata.getClassName() + " is annotated with " +
                "@Conditional(Environmental.class) but does not have the " +
                "@ApplicationEnvironment annotation";
    }
}
