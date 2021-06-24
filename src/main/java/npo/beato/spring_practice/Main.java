package npo.beato.spring_practice;

import npo.beato.spring_practice.controller.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@SpringBootApplication
@RestController
public class Main {

    public static void main(String[] args) {

        Class[] classes = controllerLoader(Main.class, PageController.class, StaticController.class, DataController.class, AdminController.class, HomeworkController.class);
        SpringApplication.run(classes, args);

    }

    private static Class[] controllerLoader(Class... controller) {
        ArrayList<Class> list = new ArrayList<>();

        for(Class c : controller) {
            list.add(c);
        }

        Class[] classes = new Class[list.size()];
        list.toArray(classes);
        return classes;
    }

}
