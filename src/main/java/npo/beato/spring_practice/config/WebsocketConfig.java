package npo.beato.spring_practice.config;

import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import npo.beato.spring_practice.controller.*;

@EnableWebSocketMessageBroker
@Configuration
public class WebsocketConfig {

}
