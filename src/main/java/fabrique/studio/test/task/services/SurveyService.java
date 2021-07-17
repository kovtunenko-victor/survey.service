package fabrique.studio.test.task.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import fabrique.studio.test.task.models.Survey;
import fabrique.studio.test.task.repositories.SurveyRepository;

public class SurveyService {
    @Autowired
    private SurveyRepository surveyRepository;
    
    public Survey saveSurvey(Survey survey) {
        return surveyRepository.save(survey);
    }
    
    public Optional<Survey> findBySurveyId(Long id) {
        return surveyRepository.findById(id);
    }
    
    public void deleteBySurveyId(Long id) {
        surveyRepository.deleteById(id);
    }
}
