package be.ordina.spring4.controller;

import be.ordina.spring4.model.Message;
import be.ordina.spring4.model.TimestampMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

/**
 * @author Ken Coenen
 */
@Controller
@RequestMapping("/")
public class ChatController {
	@RequestMapping(method = RequestMethod.GET)
	public String viewApplication() {
		return "index";
	}

	@MessageMapping("/chat")
	@SendTo("/topic/message")
	public TimestampMessage sendMessage(Message message) {
		return new TimestampMessage(message, new Date());
	}
}