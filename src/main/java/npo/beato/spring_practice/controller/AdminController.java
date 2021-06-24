package npo.beato.spring_practice.controller;

import npo.beato.spring_practice.repository.FileService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AdminController {

    @GetMapping(value = "admin")
    public String loginAdminPage_GET() {
        FileService fs = new FileService();
        StringBuilder adminText = new StringBuilder(fs.getHtml("admin_login"));
        return adminText.toString();
    }

    @GetMapping(value = "admin/page", produces = MediaType.TEXT_HTML_VALUE)
    public String adminPage_GET() {
        FileService fs = new FileService();
        StringBuilder adminText = new StringBuilder(fs.getHtml("admin"));
        return adminText.toString();
    }

    @GetMapping(value = "admin/page/{adminPageName}")
    public String adminPage(@PathVariable(value = "adminPageName") String adminPageName) {
        FileService fs = new FileService();
        StringBuilder adminPageText = new StringBuilder(fs.getHtml(adminPageName));
        return adminPageText.toString();
    }

}
