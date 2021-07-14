package fabrique.studio.test.task.repositories;

import org.springframework.data.repository.CrudRepository;

import fabrique.studio.test.task.models.Question;

public interface QuestionRepository extends CrudRepository<Question, Long>{

}
