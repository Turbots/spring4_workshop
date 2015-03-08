package be.ordina.workshop.spring4.meta.meta;

import be.ordina.workshop.spring4.meta.meta.components.MessageService;
import be.ordina.workshop.spring4.meta.meta.config.WorkshopConfiguration;
import be.ordina.workshop.spring4.meta.meta.model.ChatMessage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDateTime;

public class TestConditionalMetaAnnotation {
    private final MessageService messageService;

    public static void main(String[] args) {
        TestConditionalMetaAnnotation testConditional = new TestConditionalMetaAnnotation();

        testConditional.printMessages();
        testConditional.sendMessage(new ChatMessage("Me", "Hello Spring", LocalDateTime.now()));
        testConditional.printMessages();
    }

    public TestConditionalMetaAnnotation() {
        ApplicationContext context = new AnnotationConfigApplicationContext(WorkshopConfiguration.class);
        messageService = context.getBean(MessageService.class);
    }

    private void printMessages() {
        System.out.println("messageService.getNewMessages() = " + messageService.getNewMessages());
    }

    private void sendMessage(ChatMessage message) {
        messageService.addMessage(message);
    }

}
