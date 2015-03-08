package be.ordina.workshop.spring4.conditional.components;

import be.ordina.workshop.spring4.conditional.model.ChatMessage;

import java.util.Collection;

public interface MessageService {

    Collection<ChatMessage> getNewMessages();

    void addMessage(ChatMessage message);
}
