package fabrique.studio.test.task.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fabrique.studio.test.task.errors.SurveyServiceException;
import fabrique.studio.test.task.models.Answer;
import fabrique.studio.test.task.repositories.AnswerRepository;

@Service
public class AnswerService {
    @Autowired
    private AnswerRepository answerRepository;
    
    public Answer saveQuestion(Answer answer) throws SurveyServiceException {
        checkAnswer(answer);
        return answerRepository.save(answer);
    }
    
    public List<Answer.Response> findAllSurveysByExtUserId(long surveyId, long extUserId) {
        List<Answer.Response> responseList = new ArrayList<>();
        
        answerRepository.findAllByExtUserId(surveyId, extUserId).stream()
                .forEach(item -> responseList.add(item.new Response()));

        return responseList;
    }
    
    private void checkAnswer(Answer answer) throws SurveyServiceException {
        if (answer.getText() == null) {
            throw new SurveyServiceException("Answer text is null");
        }
        if (answer.getQuestion() == null) {
            throw new SurveyServiceException("Answer questionId is null");
        }
    }
}
