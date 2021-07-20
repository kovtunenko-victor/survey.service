package fabrique.studio.test.task.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fabrique.studio.test.task.errors.SurveyServiceException;
import fabrique.studio.test.task.models.Question;
import fabrique.studio.test.task.models.Survey;
import fabrique.studio.test.task.services.QuestionService;
import fabrique.studio.test.task.services.SurveyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/survey-service")
@Tag(name = "Question сontroller", description = "Provides only admin actions. CRUD operations for survey questions")
public class QuestionController {
    @Autowired
    QuestionService questionService;
    
    @Autowired
    SurveyService surveyService;
    
    @GetMapping(value = "/admin/question/getById/{id}", produces = "application/json")
    @Operation(
            summary = "Get question response by id", 
            description = "Find in db and return question by id. Admin action"
        )
    public Question.Response getQuestionResponseById(@PathVariable("id") long id) throws SurveyServiceException {
        return getQuestionById(id).new Response();
    }
    
    @GetMapping(value = "/admin/question/getAllBySurveryId/{id}", produces = "application/json")
    @Operation(
            summary = "Get all question response by survery id", 
            description = "Find in db and return all question by survery id. Admin action"
        )
    public List<Question.Response> getAllQuestionResponseBySurveryId(@PathVariable("id") long id) {
        return questionService.findAllBySurveryId(id);
    }
    
    @PostMapping(value = "/admin/question/create", produces = "application/json")
    @Operation(
            summary = "Create question", 
            description = "Provides question create. Admin action"
        )
    public Question.Response createQuestion(@RequestBody Question.Request request) throws SurveyServiceException {
        Survey survey = surveyService.findBySurveyId(request.getSurveyId());
        Question newQuestion = new Question(request.getText(), Question.AnswerType.valueOf(request.getAnswerType()), survey);
        
        return questionService.saveQuestion(newQuestion).new Response();
    }
    
    @PutMapping(value = "/admin/question/updateById/{id}", produces = "application/json")
    @Operation(
            summary = "Update question by id", 
            description = "Provides question update by id. Admin action"
        )
    public Question.Response updateQuestionById(@PathVariable("id") long id, @RequestBody Question.Request request) throws SurveyServiceException {
        Question question = getQuestionById(id);
        Survey survey = surveyService.findBySurveyId(request.getSurveyId());
        
        question.setText(request.getText());
        question.setAnswerType(Question.AnswerType.valueOf(request.getAnswerType()));
        question.setSurvey(survey);
        
        return questionService.saveQuestion(question).new Response();
    }
    
    @DeleteMapping(value = "/admin/question/deleteById/{id}", produces = "application/json")
    @Operation(
            summary = "Delete question by id", 
            description = "Provides question delete by id. Admin action"
        )
    public void deleteQuestionById(@PathVariable("id") long id) {
        questionService.deleteByQuestionId(id);
    }
    
    private Question getQuestionById(long id) throws SurveyServiceException {
        return questionService.findByQuestionId(id);
    }
}
