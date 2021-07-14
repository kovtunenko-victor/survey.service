package fabrique.studio.test.task.repositories;

import org.springframework.data.repository.CrudRepository;

import fabrique.studio.test.task.models.Survey;

public interface SurveyRepository extends CrudRepository<Survey, Long> {

}
