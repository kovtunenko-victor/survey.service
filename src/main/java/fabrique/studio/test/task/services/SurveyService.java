package fabrique.studio.test.task.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fabrique.studio.test.task.models.Survey;
import fabrique.studio.test.task.repositories.SurveyRepository;

@Service
public class SurveyService {
    @Autowired
    private SurveyRepository surveyRepository;
    
    public Survey saveSurvey(Survey survey) {
        return surveyRepository.save(survey);
    }
    
    public Optional<Survey> findBySurveyId(Long id) {
        return surveyRepository.findById(id);
    }
    
    public List<Survey> findAll() {
        return surveyRepository.findAll();
    }
    
    public void deleteBySurveyId(Long id) {
        surveyRepository.deleteById(id);
    }
}
