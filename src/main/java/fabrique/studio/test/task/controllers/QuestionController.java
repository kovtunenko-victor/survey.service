package fabrique.studio.test.task.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Question getQuestionById(@PathVariable("id") long id) throws Exception {
        Optional<Question> question = questionService.findByQuestionId(id);
        
        if(question.isPresent()) {
            return question.get();
        } else {
            throw new Exception(String.format("Question by id [%s] not found", id));
        }
    }
    
    @GetMapping(value = "/admin/question/getAllBySurveryId/{id}", produces = "application/json")
    public List<Question> getAllQuestionBySurveryId(@PathVariable("id") long id) throws Exception {
        return questionService.findAllBySurveryId(id);
    }
    
    @PostMapping(value = "/admin/question/create", produces = "application/json")
    public Question createQuestion(@RequestBody Question.QuestionRequest request) throws Exception {
        Survey survey = getSurveyById(request.getSurveyId());
        Question newQuestion = new Question(request.getText(), Question.AnswerType.valueOf(request.getAnswerType()), survey);
        
        return questionService.saveQuestion(newQuestion);
    }
    
    @PutMapping(value = "/admin/question/updateById/{id}", produces = "application/json")
    public Question updateQuestionById(@PathVariable("id") long id, @RequestBody Question.QuestionRequest request) throws Exception {
        Question question = getQuestionById(id);
        Survey survey = getSurveyById(request.getSurveyId());
        
        question.setText(request.getText());
        question.setAnswerType(Question.AnswerType.valueOf(request.getAnswerType()));
        question.setSurvey(survey);
        
        return questionService.saveQuestion(question);
    }
    
    @DeleteMapping(value = "/admin/question/deleteById/{id}", produces = "application/json")
    public void deleteQuestionById(@PathVariable("id") long id) throws Exception {
        questionService.deleteByQuestionId(id);
    }
    
    private Survey getSurveyById(long id) throws Exception {
        Optional<Survey> survey = surveyService.findBySurveyId(id);

        if (survey.isPresent()) {
            return survey.get();
        } else {
            throw new Exception(String.format("SurveyId [%s] not found", id));
        }
    }
}
