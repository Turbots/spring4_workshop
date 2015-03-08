package be.ordina.workshop.spring4.conditional;

import be.ordina.workshop.spring4.conditional.components.MessageService;
import be.ordina.workshop.spring4.conditional.config.WorkshopConfiguration;
import be.ordina.workshop.spring4.conditional.model.ChatMessage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDateTime;

public class TestConditional {
    private ApplicationContext context;
    private final MessageService messageService;

    public static void main(String[] args) {
        TestConditional testConditional = new TestConditional();

        testConditional.printMessages();
        testConditional.sendMessage(new ChatMessage("Me", "Hello Spring", LocalDateTime.now()));
        testConditional.printMessages();
    }

    public TestConditional() {
        context = new AnnotationConfigApplicationContext(WorkshopConfiguration.class);
        messageService = context.getBean(MessageService.class);
    }

    private void printMessages() {
        System.out.println("messageService.getNewMessages() = " + messageService.getNewMessages());
    }

    private void sendMessage(ChatMessage message) {
        messageService.addMessage(message);
    }
}
