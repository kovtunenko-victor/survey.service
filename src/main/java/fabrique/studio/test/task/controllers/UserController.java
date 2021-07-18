package fabrique.studio.test.task.controllers;

import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fabrique.studio.test.task.models.Survey;

@RestController
@RequestMapping("/survey-service")
public class UserController {
    @GetMapping
    public Survey showStatus() {
        return new Survey("Survey name", "Survey title", new Date(), new Date());
    }
}
