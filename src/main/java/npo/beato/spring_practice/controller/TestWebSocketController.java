package npo.beato.spring_practice.controller;

import npo.beato.spring_practice.model.ws.Greeting;
import npo.beato.spring_practice.model.ws.HelloMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class TestWebSocketController {

    @MessageMapping(value = "/hello")
    @SendTo(value = "/topic/greetings")
    public Greeting greeting(HelloMessage message) {

        return new Greeting("Hello," + HtmlUtils.htmlEscape(message.getName()) + "!");
    }
}
