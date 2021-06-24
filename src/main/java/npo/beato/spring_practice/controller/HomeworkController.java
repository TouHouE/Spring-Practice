package npo.beato.spring_practice.controller;


import npo.beato.spring_practice.repository.FileService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
public class HomeworkController {

    @GetMapping(value="homework")
    public String homeworkIndex_GET() {
        FileService service = new FileService();
        service.setRoot("homework_static");
        String index = service.getHtml("index.html");
        return index;
    }

    @GetMapping(value="homework2", produces = MediaType.TEXT_HTML_VALUE)
    public String homework2Index_GET() {
        FileService service = new FileService();
        service.setRoot("homework_static");
        String index = service.getHtml("regression2.html");
        return index;
    }

    @GetMapping(value = "homework/js/{jsName}", produces = "text/javascript")
    @ResponseBody
    public String linearRegressionJS_GET(@PathVariable(value="jsName") String jsName) {
        FileService service = new FileService();
        service.setRoot("homework_static");
        String js = service.getJs(jsName);
        return js;
    }

    @GetMapping(value="homework/data/{dataName}", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String data_GET(@PathVariable(value="dataName") String dataName) {
        FileService service = new FileService();
        service.setRoot("homework_static");
        String text = service.getTextData(dataName);
        return text;
    }

}
