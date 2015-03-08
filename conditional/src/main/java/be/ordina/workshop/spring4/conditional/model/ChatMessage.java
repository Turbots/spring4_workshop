package be.ordina.workshop.spring4.conditional.model;

import com.sun.istack.internal.NotNull;

import java.time.LocalDateTime;
import java.util.Objects;

public class ChatMessage {

    private final String sender;
    private final String message;
    private final LocalDateTime timeStamp;

    public ChatMessage(@NotNull String sender, @NotNull String message, @NotNull LocalDateTime timeStamp) {
        this.sender = sender;
        this.message = message;
        this.timeStamp = timeStamp;
    }

    public String getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChatMessage that = (ChatMessage) o;
        return Objects.equals(message, that.message)
                && Objects.equals(sender, that.sender)
                && Objects.equals(timeStamp, that.timeStamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sender, message, timeStamp);
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "sender='" + sender + '\'' +
                ", message='" + message + '\'' +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
