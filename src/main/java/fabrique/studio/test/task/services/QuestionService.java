package fabrique.studio.test.task.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import fabrique.studio.test.task.models.Question;

import fabrique.studio.test.task.repositories.QuestionRepository;

public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    
    public void saveQuestion(Question question) {
        questionRepository.save(question);
    }
    
    public Optional<Question> findByQuestionId(Long id) {
        return questionRepository.findById(id);
    }
    
    public void deleteByQuestionId(Long id) {
        questionRepository.deleteById(id);
    }
}
