package com.raj.socket.all;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

public class MyHandler extends TextWebSocketHandler {
    @Override
    protected void handleTextMessage(WebSocketSession session, @Payload TextMessage message) throws Exception {
        System.out.println("Got message: " + message.getPayload());
        String messagePayload = message.getPayload();
        processMessage(session, messagePayload);

    }

    private void processMessage(WebSocketSession session, String message) {
        String msg = "boliye sir";
        try {
            String value = new ObjectMapper().writeValueAsString(msg);
            session.sendMessage(new TextMessage(value));
        } catch (IOException e) {
            System.out.println("Session with id: " + session.getId() + "  is already closed by host!");
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        System.out.println("WebSocket connected with sessionId:  " + session.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        System.out.println("WebSocket disconnected from sessionId:  " + session.getId());
    }
}
