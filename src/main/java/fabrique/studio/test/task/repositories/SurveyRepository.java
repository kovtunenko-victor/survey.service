package fabrique.studio.test.task.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fabrique.studio.test.task.models.Survey;

public interface SurveyRepository extends JpaRepository<Survey, Long> {

}
