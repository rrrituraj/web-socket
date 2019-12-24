package com.raj.socket.all;

import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

public class MyHandler extends TextWebSocketHandler {
    @Override
    protected void handleTextMessage(WebSocketSession session, @Payload TextMessage message) throws Exception {
        System.out.println("Got message\n: " + message.getPayload());
        String messagePayload = message.getPayload();
        processMessage(session, messagePayload);

    }

    private void processMessage(WebSocketSession session, String message) throws IOException, InterruptedException {
        String msg = "boliye sir";
        try {
            session.sendMessage(new TextMessage(message + msg));
            Thread.sleep(3000);
            session.sendMessage(new TextMessage(message + msg));
        } catch (IOException e) {
            System.out.println("Session with id: " + session.getId() + "  is already closed by host!");
        }
    }
}
