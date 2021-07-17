package fabrique.studio.test.task.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fabrique.studio.test.task.models.Response;

@RestController
@RequestMapping("/survey-service")
public class SurveryAdminController {
    
    @GetMapping
    public Response showStatus() {
        return new Response("Service alive");
    }
    
    @GetMapping(value = "/admin/test", produces = "application/json")
    public Response test() {
        return new Response("test");
    }
    
    @GetMapping(value = "/admin/survery/getById", produces = "application/json")
    public Response getSurveyById() {
        return new Response("test");
    }
    
    @GetMapping(value = "/admin/survery/getAll", produces = "application/json")
    public Response getAllSurvey() {
        return new Response("test");
    }
    
    @PostMapping(value = "/admin/survery/create", produces = "application/json")
    public Response createSurvey() {
        return new Response("test");
    }
    
    @PutMapping(value = "/admin/survery/updateById", produces = "application/json")
    public Response updateSurveyById() {
        return new Response("test");
    }
    
    @DeleteMapping(value = "/admin/survery/deleteById", produces = "application/json")
    public Response deleteSurveyById() {
        return new Response("test");
    }
}
