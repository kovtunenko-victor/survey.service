package fabrique.studio.test.task.models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import fabrique.studio.test.task.errors.SurveyServiceException;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "answer")
@Getter
@Setter
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private long answerId;
    
    private String text;
    
    @Column(name = "ext_user_id")
    private long extUserId;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "questionId")
    private Question question;
    
    public Answer() {
        
    }
    
    public Answer(String text, long extUserId, Question question) {
        this.text = text;
        this.extUserId = extUserId;
        this.question = question;
    }
    
    @Override
    public boolean equals(Object o) {

      if (this == o)
        return true;
      if (!(o instanceof Answer))
        return false;
      Answer answer = (Answer) o;
      return Objects.equals(this.answerId, answer.answerId) && Objects.equals(this.text, answer.text)
              && Objects.equals(this.extUserId, answer.extUserId) && Objects.equals(this.question, answer.question);
    }

    @Override
    public int hashCode() {
      return Objects.hash(this.answerId, this.text, this.extUserId, this.question);
    }
    
    @Getter
    @Setter
    public static class Request {
        private long questionId;
        private long extUserId;
        private String text;
    }
    
    public class Response {
        public long getAnswerId() {
            return Answer.this.getAnswerId();
        }
        
        public long getQuestionId() throws SurveyServiceException {
            if(Answer.this.getQuestion() != null) {
                return Answer.this.getQuestion().getQuestionId(); 
            } else {
                throw new SurveyServiceException("Answer questionId is null");
            }
        }
        
        public long getExtUserId() {
            return Answer.this.getExtUserId();
        }
        
        public String getText() {
            return Answer.this.getText();
        }
    }
}
