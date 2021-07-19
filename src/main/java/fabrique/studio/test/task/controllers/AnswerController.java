package fabrique.studio.test.task.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fabrique.studio.test.task.errors.SurveyServiceException;
import fabrique.studio.test.task.models.Answer;
import fabrique.studio.test.task.models.Question;
import fabrique.studio.test.task.repositories.AnswerRepository;
import fabrique.studio.test.task.services.QuestionService;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/survey-service")
public class AnswerController {
    @Autowired
    AnswerRepository answerRepository;
    
    @Autowired
    QuestionService questionService;
    
    @PostMapping(value = "/answer/create", produces = "application/json")
    public Answer.Response createAnswer(@RequestBody Answer.Request request) throws SurveyServiceException {
        Question question = questionService.findByQuestionId(request.getQuestionId());
        Answer newAnswer = new Answer(request.getText(), request.getExtUserId(), question);
        
        return answerRepository.save(newAnswer).new Response();
    }
}


