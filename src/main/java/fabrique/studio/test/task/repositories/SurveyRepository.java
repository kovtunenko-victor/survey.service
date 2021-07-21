package fabrique.studio.test.task.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fabrique.studio.test.task.models.Survey;

public interface SurveyRepository extends JpaRepository<Survey, Long> {
    @Query("select s from Survey s where s.dateEnd >= CURRENT_DATE and s.dateStart <= CURRENT_DATE")
    List<Survey> findAllActiveSurveys();
    
    @Query("select distinct s from Survey s join s.questions q join q.answers a where a.extUserId = :extUserId")
    List<Survey> findAllByExtUserId(@Param("extUserId") long id);
}
