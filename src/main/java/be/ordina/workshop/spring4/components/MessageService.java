package be.ordina.workshop.spring4.components;

import be.ordina.workshop.spring4.model.ChatMessage;

import java.util.Collection;

public interface MessageService {

    Collection<ChatMessage> getNewMessages();

    void addMessage(ChatMessage message);
}
