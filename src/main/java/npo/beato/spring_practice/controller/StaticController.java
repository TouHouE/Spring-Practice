package npo.beato.spring_practice.controller;

import npo.beato.spring_practice.model.ImageData;
import npo.beato.spring_practice.repository.FileService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
public class StaticController {

    @GetMapping("static/js/{jsName}")
    public String getJs(@PathVariable("jsName") String jsName) {
        FileService service = new FileService();
        StringBuilder js = new StringBuilder(service.getJs(jsName));
        return js.toString();
    }

    @GetMapping("static/js/{pathName}/{jsName}")
    public String getJs(@PathVariable(value = "pathName") String pathName, @PathVariable(value = "jsName") String jsName) {
        FileService service = new FileService();
        StringBuilder js = new StringBuilder();
        return js.toString();
    }

    @GetMapping("static/css/{cssName}")
    public String getCss(@PathVariable(value = "cssName") String cssName) {
        System.out.println("get:" + cssName);

        FileService service = new FileService();
        StringBuilder css = new StringBuilder(service.getCss(cssName));
        return css.toString();
    }


    //get icon
    @GetMapping(value = "/static/icon", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] getIcon() throws IOException {
        FileInputStream fis = new FileInputStream(new File("docs/static/icon.jpg"));
        byte[] bytes = new byte[fis.available()];
        fis.read(bytes, 0, fis.available());
        return bytes;
    }

    @GetMapping(value = "/static/img/{imgName}")
    @ResponseBody
    public byte[] getImg(@PathVariable(value = "imgName") String imgName) {
        FileService fileS = new FileService();
        ImageData imageData = fileS.getImg(imgName);

        return imageData.getImgBytes();
    }
}
