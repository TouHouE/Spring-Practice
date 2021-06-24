package npo.beato.spring_practice.controller;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;


public class WebSocketController extends TextWebSocketHandler{
    private HashMap<String, WebSocketSession> sessions = new HashMap<>();
    private AtomicInteger count = new AtomicInteger(0);

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessions.remove(session.getId());

    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessions.put(session.getId(), session);
        count.incrementAndGet();
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        System.out.printf("%s>%s\n", session.getId(), message);
    }

}
