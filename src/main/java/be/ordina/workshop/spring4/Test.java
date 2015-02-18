package be.ordina.workshop.spring4;

import be.ordina.workshop.spring4.components.MessageService;
import be.ordina.workshop.spring4.config.WorkshopConfiguration;
import be.ordina.workshop.spring4.model.ChatMessage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDateTime;

public class Test {
    private ApplicationContext context;
    private final MessageService messageService;

    public static void main(String[] args) {
        Test test = new Test();

        test.printMessages();
        test.sendMessage(new ChatMessage("Me", "Hello Spring", LocalDateTime.now()));
        test.printMessages();
    }

    public Test() {
        System.out.println("Creating context");
        context = new AnnotationConfigApplicationContext(WorkshopConfiguration.class);
        System.out.println("Context created, requesting bean");
        messageService = context.getBean(MessageService.class);
        System.out.println("Bean returned");
    }

    private void printMessages() {
        System.out.println("messageService.getNewMessages() = " + messageService.getNewMessages());
    }

    private void sendMessage(ChatMessage message) {
        messageService.addMessage(message);
    }
}
