package fabrique.studio.test.task.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fabrique.studio.test.task.errors.SurveyServiceException;
import fabrique.studio.test.task.models.Question;
import fabrique.studio.test.task.repositories.QuestionRepository;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    public Question saveQuestion(Question question) throws SurveyServiceException {
        checkQuestion(question);
        return questionRepository.save(question);
    }

    public Question findByQuestionId(long id) throws SurveyServiceException {
        Optional<Question> question = questionRepository.findById(id);

        if (question.isPresent()) {
            return question.get();
        } else {
            throw new SurveyServiceException(String.format("Question by id [%s] not found", id));
        }
    }

    public List<Question.Response> findAllBySurveryId(long id) {
        List<Question.Response> responseList = new ArrayList<>();
        questionRepository.findAllBySurveryId(id).stream().forEach(item -> responseList.add(item.new Response()));
        
        return responseList;
    }

    public void deleteByQuestionId(long id) {
        questionRepository.deleteById(id);
    }

    private void checkQuestion(Question question) throws SurveyServiceException {
        if (question.getText() == null) {
            throw new SurveyServiceException("Question text is null");
        }
        if (question.getAnswerType() == null) {
            throw new SurveyServiceException("Question answerType is null");
        }
        if (question.getSurvey() == null) {
            throw new SurveyServiceException("Question surveyId is null");
        }
    }
}
