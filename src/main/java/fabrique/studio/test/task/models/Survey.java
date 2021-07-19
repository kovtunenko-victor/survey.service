package fabrique.studio.test.task.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import fabrique.studio.test.task.errors.SurveyServiceException;
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
    private long surveyId;
    private String name;
    private String title;
    private Date dateStart;
    private Date dateEnd;
    
    @OneToMany(mappedBy = "survey")
    Set<Question> questions;
    
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
      return Objects.equals(this.surveyId, survey.surveyId) && Objects.equals(this.name, survey.name)
          && Objects.equals(this.title, survey.title) && Objects.equals(this.dateStart, survey.dateStart) 
          && Objects.equals(this.dateEnd, survey.dateEnd);
    }

    @Override
    public int hashCode() {
      return Objects.hash(this.surveyId, this.name, this.title, this.dateStart, this.dateEnd);
    }
    
    @Getter
    @Setter
    public static class Request {
        private String name;
        private String title;
        private Date dateStart;
        private Date dateEnd;
    }
    
    public class Response {
        public long getSurveyId() {
            return Survey.this.getSurveyId();
        }
        
        public String getName() {
            return Survey.this.getName();
        }
        
        public String getTitle() throws SurveyServiceException {
            return Survey.this.getTitle();
        }
        
        public Date getDateStart() {
            return Survey.this.getDateStart();
        }
        
        public Date getDateEnd() {
            return Survey.this.getDateEnd();
        }
    }
    
    public class ResponseWithAnswer extends Response {
        private List<Answer.Response> answers;
        
        public ResponseWithAnswer() {
            this.answers = new ArrayList<>();
        }
        
        public ResponseWithAnswer(List<Answer.Response> answers) {
            this.answers = answers;
        }
        
        public  List<Answer.Response> getAnswers() {
            return answers;
        }
    }
}
