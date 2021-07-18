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

import fabrique.studio.test.task.models.Survey;
import fabrique.studio.test.task.services.SurveyService;

@RestController
@RequestMapping("/survey-service")
public class SurveryController {
    @Autowired
    SurveyService surveyService;

    @GetMapping(value = "/admin/survery/getById/{id}", produces = "application/json")
    public Survey getSurveyById(@PathVariable("id") long id) throws Exception {
        Optional<Survey> survey = surveyService.findBySurveyId(id);

        if (survey.isPresent()) {
            return survey.get();
        } else {
            throw new Exception(String.format("SurveyId [%s] not found", id));
        }
    }

    @GetMapping(value = "/admin/survery/getAll", produces = "application/json")
    public List<Survey> getAllSurvey() throws Exception {
        return surveyService.findAll();
    }

    @PostMapping(value = "/admin/survery/create", produces = "application/json")
    public Survey createSurvey(@RequestBody Survey.SurveyRequest request) throws Exception {
        Survey newSurvey = new Survey(request.getName(), request.getTitle(), request.getDateStart(), request.getDateEnd());
        return surveyService.saveSurvey(newSurvey);
    }

    @PutMapping(value = "/admin/survery/updateById/{id}", produces = "application/json")
    public Survey updateSurveyById(@PathVariable("id") long id, @RequestBody Survey.SurveyRequest request) throws Exception {
        Survey survey = getSurveyById(id);
        
        survey.setName(request.getName());
        survey.setTitle(request.getTitle());
        survey.setDateStart(request.getDateStart());
        survey.setDateEnd(request.getDateEnd());
        
        return surveyService.saveSurvey(survey);
    }

    @DeleteMapping(value = "/admin/survery/deleteById/{id}", produces = "application/json")
    public void deleteSurveyById(@PathVariable("id") long id) throws Exception {
        surveyService.deleteBySurveyId(id);
    }
}
