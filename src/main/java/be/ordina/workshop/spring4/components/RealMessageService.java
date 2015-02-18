package be.ordina.workshop.spring4.components;

import be.ordina.workshop.spring4.model.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;

@Description("Actual message service. Can only be used in production")
public class RealMessageService implements MessageService {

    private Collection<ChatMessage> messages = new ArrayList<>();

    @Autowired
    private LazyService lazyService;

    @PostConstruct
    private void postConstruct() {
        System.out.println("\n\n================\nREAL message service bean created\n================\n\n");
    }

    @Override
    public Collection<ChatMessage> getNewMessages() {
        return new ArrayList<>(messages);
    }

    @Override
    public void addMessage(ChatMessage message) {
        messages.add(message);
        lazyService.doSomething();
    }

}
