package npo.beato.spring_practice.controller;


//import com.sun.org.apache.xpath.internal.operations.String;
import npo.beato.spring_practice.repository.FileService;
import org.springframework.web.bind.annotation.*;

import npo.beato.spring_practice.device.DeviceType;


import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class PageController {

    private volatile Map<String, String> htmlData = new HashMap<>();
    private volatile Map<String, String> htmlMobileData = new HashMap<>();


    @GetMapping("/")
    public String getHomePage(HttpServletRequest request) {


        FileService fileS = new FileService();
        StringBuilder homePage = new StringBuilder(fileS.getHtml("index"));
        System.out.println(request.getRemoteAddr());
        return homePage.toString();

    }

    @GetMapping("/page/{pageName}")
    public String getPage(HttpServletRequest request, @PathVariable(value = "pageName") String pageName) {
        FileService fileS = new FileService();
        StringBuilder page = new StringBuilder(fileS.getHtml(pageName));
        return page.toString();
    }

    @GetMapping("/page/{pathName}/{pageName}")
    public String getPage(@PathVariable(value="pathName") String pathName, @PathVariable(value="pageName") String pageName) {
        FileService fs = new FileService();
        StringBuilder page = new StringBuilder(fs.getHtml(pathName, pageName));

        return page.toString();
    }

    @GetMapping("/note")
    public String getNote(HttpServletRequest request) {
        FileService fileS = new FileService();
        StringBuilder notePage = new StringBuilder(fileS.getHtml("note"));
        return notePage.toString();

    }


    @PostMapping(value = "/game/post", produces = "text/html;charset=UTF-8")
    public void getGamePage(@RequestBody Object obj) {
        LinkedHashMap<String, String> requestMap = (LinkedHashMap)obj;

        FileService fileS = new FileService();
        StringBuilder gamePage = new StringBuilder(fileS.getHtml("Puzzle", "index"));
        String page = gamePage.toString();
        page = page.replaceFirst("<!--serial-->", requestMap.get("serial"));

        DeviceType deviceType = new DeviceType();
        System.out.println(requestMap.get("os_type"));

        if(deviceType.isMob(requestMap.get("os_type"))) {
           page = page.replaceAll("__mousedown__", "touchstart");
           page = page.replaceAll("__mousemove__", "touchmove");
           page = page.replaceAll("__mouseup__", "touchend");
           page = page.replaceAll("//false;", "true;");
           htmlMobileData.put(requestMap.get("serial"), page);
        } else {
            page = page.replaceAll("__mousedown__", "mousedown");
            page = page.replaceAll("__mousemove__", "mousemove");
            page = page.replaceAll("__mouseup__", "mouseup");
            page = page.replaceAll("//false;", "false;");
            htmlData.put(requestMap.get("serial"), page);
        }

    }

    @GetMapping(value = "/game/{serial}", produces = "text/html;charset=UTF-8")
    public String getGamePage(@PathVariable(value = "serial")String serial) {
        String html = htmlData.get(serial);

        System.out.println(html);
        return html;
    }

    @GetMapping(value = "/mob/game/{serial}", produces = "text/html;charset=UTF-8")
    public String getMobileGamePage(@PathVariable(value = "serial") String serial) {
        String html = htmlMobileData.get(serial);

        System.out.println(html);
        return html;
    }

}
