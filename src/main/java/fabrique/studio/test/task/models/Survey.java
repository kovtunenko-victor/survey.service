package fabrique.studio.test.task.models;

import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "surveys")
@Getter
@Setter
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long survey_id;
    private String name;
    private String title;
    private Date dateStart;
    private Date dateEnd;
    
    public Survey( ) {
        
    }
    
    public Survey(String name, String title, Date dateStart, Date dateEnd) {
        this.name = name;
        this.title = title;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }
    
    @Override
    public boolean equals(Object o) {

      if (this == o)
        return true;
      if (!(o instanceof Survey))
        return false;
      Survey survey = (Survey) o;
      return Objects.equals(this.survey_id, survey.survey_id) && Objects.equals(this.name, survey.name)
          && Objects.equals(this.title, survey.title) && Objects.equals(this.dateStart, survey.dateStart) 
          && Objects.equals(this.dateEnd, survey.dateEnd);
    }

    @Override
    public int hashCode() {
      return Objects.hash(this.survey_id, this.name, this.title, this.dateStart, this.dateEnd);
    }
    
    @Getter
    @Setter
    public static class SurveyRequest {
        private String name;
        private String title;
        private Date dateStart;
        private Date dateEnd;
    }
}
