package be.ordina.workshop.spring4.meta.meta.components;


import be.ordina.workshop.spring4.meta.meta.model.ChatMessage;

import java.util.Collection;

public interface MessageService {

    Collection<ChatMessage> getNewMessages();

    void addMessage(ChatMessage message);
}
