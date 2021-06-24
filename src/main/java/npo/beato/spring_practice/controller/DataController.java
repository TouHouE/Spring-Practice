package npo.beato.spring_practice.controller;

import npo.beato.spring_practice.Main;
import npo.beato.spring_practice.device.DeviceType;
import npo.beato.spring_practice.repository.FileService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import sun.misc.IOUtils;

import javax.imageio.ImageIO;
import javax.print.attribute.standard.Media;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

@Controller
public class DataController {

    private ArrayList<String> okUsers = new ArrayList<>();

    //Admin part
    @PostMapping(value = "/admin/post", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> usePost2GetData(@RequestBody Object obj) {
        Map<String, String> data = (HashMap<String, String>)obj;
        String url = "http://itc.nkust.edu.tw/admin/page";
        Map<String, Object> response = new HashMap<>();

        String userName = (String)data.get("user_name");
        String password = (String)data.get("password");
        System.out.printf("User Name:%s, password:%s\n", userName, password);


        if(userName.equals("beatrice") && password.equals("a25283218")) {
            response.put("url", url);
            System.out.println(response.toString());
            return response;
        } else {
            return null;
        }

        //System.out.println(response);
        //return response;
    }

    @PostMapping(value = "/user/{serial}")
    public void clientSendOK(@PathVariable String serial) {
        okUsers.add(serial);
    }

    @PostMapping(value="/admin/page/post", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, String> savePage(@RequestBody Object obj) {
        Map<String, Object> dataJS = (HashMap<String, Object>)obj;

        Set<String> keys = dataJS.keySet();

        System.out.println("RUN");

        for(String key : keys) {
            System.out.printf("Key:%s value:%s\n", key, dataJS.get(key).toString());
        }

/*
        String content = dataJS.get("content");
        FileService fs = new FileService();
        String response;
        System.out.println("POST Page");
        System.out.println(content);


        if(dataJS.containsKey("file_name")) {
            String fileName = dataJS.get("file_name");
            response = fs.saveHtml(content, fileName);


        } else {

            if(content.matches(".*\\.html")) {
                response = fs.saveHtml(content);
            } else if(content.matches(".*\\.txt")) {
                response = fs.saveTxt(content);
            } else {
                response = "WTF!!!";
            }


        }
        System.out.println(response);
*/
        Map<String, String> resJSON = new HashMap<String, String>();
        resJSON.put("status", "Testing");

        return resJSON;
    }

}
