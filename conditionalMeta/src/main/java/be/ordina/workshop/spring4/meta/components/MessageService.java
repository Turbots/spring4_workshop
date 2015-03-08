package be.ordina.workshop.spring4.meta.components;


import be.ordina.workshop.spring4.meta.model.ChatMessage;

import java.util.Collection;

public interface MessageService {

    Collection<ChatMessage> getNewMessages();

    void addMessage(ChatMessage message);
}
