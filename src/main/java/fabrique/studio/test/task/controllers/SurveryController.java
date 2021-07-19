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
import fabrique.studio.test.task.models.Survey;
import fabrique.studio.test.task.services.SurveyService;

@RestController
@RequestMapping("/survey-service")
public class SurveryController {
    @Autowired
    SurveyService surveyService;
    
    @GetMapping(value = "/survery/getAllActiveSurveys", produces = "application/json")
    public List<Survey.Response> getAllActiveSurveys() {
        return surveyService.findAllActiveSurveys();
    }
    
    @GetMapping(value = "/survery/getAllSurveysByExtUserId/{id}", produces = "application/json")
    public List<Survey.ResponseWithAnswer> getAllSurveysByExtUserId(@PathVariable("id") long id) {
        return surveyService.findAllSurveysByExtUserId(id);
    }

    @GetMapping(value = "/admin/survery/getById/{id}", produces = "application/json")
    public Survey.Response getSurveyResponseById(@PathVariable("id") long id) throws SurveyServiceException {
        return getSurveyById(id).new Response();
    }

    @GetMapping(value = "/admin/survery/getAll", produces = "application/json")
    public List<Survey.Response> getAllSurvey() {
        return surveyService.findAll();
    }

    @PostMapping(value = "/admin/survery/create", produces = "application/json")
    public Survey createSurvey(@RequestBody Survey.Request request) throws SurveyServiceException {
        Survey newSurvey = new Survey(request.getName(), request.getTitle(), request.getDateStart(), request.getDateEnd());
        return surveyService.saveSurvey(newSurvey);
    }

    @PutMapping(value = "/admin/survery/updateById/{id}", produces = "application/json")
    public Survey updateSurveyById(@PathVariable("id") long id, @RequestBody Survey.Request request) throws SurveyServiceException {
        Survey survey = getSurveyById(id);
        
        survey.setName(request.getName());
        survey.setTitle(request.getTitle());
        survey.setDateEnd(request.getDateEnd());
        
        return surveyService.saveSurvey(survey);
    }

    @DeleteMapping(value = "/admin/survery/deleteById/{id}", produces = "application/json")
    public void deleteSurveyById(@PathVariable("id") long id) {
        surveyService.deleteBySurveyId(id);
    }
    
    private Survey getSurveyById(@PathVariable("id") long id) throws SurveyServiceException {
        return surveyService.findBySurveyId(id);
    }
}
