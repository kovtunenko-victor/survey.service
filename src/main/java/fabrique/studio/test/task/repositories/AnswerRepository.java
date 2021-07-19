package fabrique.studio.test.task.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fabrique.studio.test.task.models.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    @Query("select a from Survey s join s.questions q join q.answers a where a.extUserId = :extUserId and s.surveyId = :surveyId")
    List<Answer> findAllByExtUserId(@Param("surveyId") long surveyId, @Param("extUserId") long extUserId);
}
