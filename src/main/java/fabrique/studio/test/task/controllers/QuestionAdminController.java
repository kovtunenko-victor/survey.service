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
public class QuestionAdminController {
    @GetMapping(value = "/admin/question/getById", produces = "application/json")
    public Response getQuestionById() {
        return new Response("test");
    }
    
    @GetMapping(value = "/admin/question/getAllBySurveryId", produces = "application/json")
    public Response getAllQuestionBySurveryId() {
        return new Response("test");
    }
    
    @PostMapping(value = "/admin/question/create", produces = "application/json")
    public Response createQuestion() {
        return new Response("test");
    }
    
    @PutMapping(value = "/admin/question/updateById", produces = "application/json")
    public Response updateQuestionById() {
        return new Response("test");
    }
    
    @DeleteMapping(value = "/admin/question/deleteById", produces = "application/json")
    public Response deleteQuestionById() {
        return new Response("test");
    }
}
