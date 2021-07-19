package fabrique.studio.test.task.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fabrique.studio.test.task.errors.SurveyServiceException;
import fabrique.studio.test.task.models.Survey;
import fabrique.studio.test.task.repositories.SurveyRepository;

@Service
public class SurveyService {
    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private AnswerService answerService;

    public Survey saveSurvey(Survey survey) throws SurveyServiceException {
        checkSurvey(survey);
        return surveyRepository.save(survey);
    }

    public Survey findBySurveyId(long id) throws SurveyServiceException {
        Optional<Survey> survey = surveyRepository.findById(id);

        if (survey.isPresent()) {
            return survey.get();
        } else {
            throw new SurveyServiceException(String.format("SurveyId [%s] not found", id));
        }
    }

    public List<Survey.Response> findAll() {
        List<Survey.Response> responseList = new ArrayList<>();
        surveyRepository.findAll().stream().forEach(item -> responseList.add(item.new Response()));

        return responseList;
    }

    public List<Survey.Response> findAllActiveSurveys() {
        List<Survey.Response> responseList = new ArrayList<>();
        surveyRepository.findAllActiveSurveys().stream().forEach(item -> responseList.add(item.new Response()));

        return responseList;
    }

    public List<Survey.ResponseWithAnswer> findAllSurveysByExtUserId(long extUserId) {
        List<Survey.ResponseWithAnswer> responseList = new ArrayList<>();

        surveyRepository.findAllByExtUserId(extUserId).stream().forEach(item -> responseList.add(
                item.new ResponseWithAnswer(answerService.findAllSurveysByExtUserId(item.getSurveyId(), extUserId))));

        return responseList;
    }

    public void deleteBySurveyId(long id) {
        surveyRepository.deleteById(id);
    }

    public void checkSurvey(Survey survey) throws SurveyServiceException {
        if (survey.getName() == null) {
            throw new SurveyServiceException("Survey name is null");
        }
        if (survey.getTitle() == null) {
            throw new SurveyServiceException("Survey title is null");
        }
        if (survey.getDateStart() == null) {
            throw new SurveyServiceException("Survey startDate is null");
        }
        if (survey.getDateEnd() == null) {
            throw new SurveyServiceException("Survey endDate is null");
        }
        if (survey.getDateStart().getTime() >= survey.getDateEnd().getTime()) {
            throw new SurveyServiceException("Survey endDate >= startDate");
        }
    }
}
