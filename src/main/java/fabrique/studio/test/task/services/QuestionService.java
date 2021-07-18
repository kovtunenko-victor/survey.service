package fabrique.studio.test.task.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fabrique.studio.test.task.models.Question;

import fabrique.studio.test.task.repositories.QuestionRepository;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    
    public Question saveQuestion(Question question) {
        return questionRepository.save(question);
    }
    
    public Optional<Question> findByQuestionId(Long id) {
        return questionRepository.findById(id);
    }
    
    public List<Question> findAllBySurveryId(Long id) {
        return questionRepository.findAllBySurveryId(id);
    }
    
    public void deleteByQuestionId(Long id) {
        questionRepository.deleteById(id);
    }
}
