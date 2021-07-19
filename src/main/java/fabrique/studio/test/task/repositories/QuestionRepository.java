package fabrique.studio.test.task.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fabrique.studio.test.task.models.Question;

public interface QuestionRepository extends JpaRepository<Question, Long>{
    @Query("select q from Question q where q.survey.surveyId = :surveyId")
    List<Question> findAllBySurveryId(@Param("surveyId") long id);
}
