package be.ordina.workshop.spring4.meta.components;


import be.ordina.workshop.spring4.meta.condition.annotations.Testing;
import be.ordina.workshop.spring4.meta.model.ChatMessage;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import static java.time.LocalDateTime.now;

@Service
@Description("Fake message service for testing purposes")
@Testing
public class FakeMessageService implements MessageService {
    private Collection<ChatMessage> messages = new ArrayList<>();

    {
        LocalDateTime now = now();
        messages.add(new ChatMessage("test1", "test1", now.minusHours(1)));
        messages.add(new ChatMessage("test", "test", now));
    }

    @PostConstruct
    private void init() {
        System.out.println("\n\n================\nFAKE message service bean created\n================\n\n");
    }

    @Override
    public Collection<ChatMessage> getNewMessages() {
        return new ArrayList<>(messages);
    }

    @Override
    public void addMessage(ChatMessage message) {
        //Do nothing.
    }
}
