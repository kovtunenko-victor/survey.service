package fabrique.studio.test.task.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fabrique.studio.test.task.models.Response;

@RestController
@RequestMapping("/survey-service")
public class AdminController {
    
    @GetMapping
    public Response showStatus() {
        return new Response("Service alive");
    }
    
    @GetMapping(value = "/test", produces = "application/json")
    public Response test() {
        return new Response("test");
    }
}
