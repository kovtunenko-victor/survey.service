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

@RestController
@RequestMapping("/survey-service")
public class QuestionController {
    @Autowired
    QuestionService questionService;
    
    @Autowired
    SurveyService surveyService;
    
    @GetMapping(value = "/admin/question/getById/{id}", produces = "application/json")
    public Question.Response getQuestionResponseById(@PathVariable("id") long id) throws SurveyServiceException {
        return getQuestionById(id).new Response();
    }
    
    @GetMapping(value = "/admin/question/getAllBySurveryId/{id}", produces = "application/json")
    public List<Question.Response> getAllQuestionResponseBySurveryId(@PathVariable("id") long id) {
        return questionService.findAllBySurveryId(id);
    }
    
    @PostMapping(value = "/admin/question/create", produces = "application/json")
    public Question createQuestion(@RequestBody Question.QuestionRequest request) throws SurveyServiceException {
        Survey survey = surveyService.findBySurveyId(request.getSurveyId());
        Question newQuestion = new Question(request.getText(), Question.AnswerType.valueOf(request.getAnswerType()), survey);
        
        return questionService.saveQuestion(newQuestion);
    }
    
    @PutMapping(value = "/admin/question/updateById/{id}", produces = "application/json")
    public Question updateQuestionById(@PathVariable("id") long id, @RequestBody Question.QuestionRequest request) throws SurveyServiceException {
        Question question = getQuestionById(id);
        Survey survey = surveyService.findBySurveyId(request.getSurveyId());
        
        question.setText(request.getText());
        question.setAnswerType(Question.AnswerType.valueOf(request.getAnswerType()));
        question.setSurvey(survey);
        
        return questionService.saveQuestion(question);
    }
    
    @DeleteMapping(value = "/admin/question/deleteById/{id}", produces = "application/json")
    public void deleteQuestionById(@PathVariable("id") long id) {
        questionService.deleteByQuestionId(id);
    }
    
    private Question getQuestionById(long id) throws SurveyServiceException {
        return questionService.findByQuestionId(id);
    }
}
