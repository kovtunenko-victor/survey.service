package fabrique.studio.test.task.models;

import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import fabrique.studio.test.task.errors.SurveyServiceException;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "question")
@Getter
@Setter

@Hidden
@Schema(description = "Question entity")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private long questionId;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "surveyId")
    private Survey survey;
    private String text;
    
    @Enumerated(EnumType.STRING)
    private AnswerType answerType;
    
    @OneToMany(mappedBy = "question")
    Set<Answer> answers;
    
    public Question() {
       
    }
    
    public Question(String text, Question.AnswerType answerType, Survey survey) {
        this.text = text;
        this.answerType = answerType;
        this.survey = survey;
    }
    
    @Override
    public boolean equals(Object o) {

      if (this == o)
        return true;
      if (!(o instanceof Question))
        return false;
      Question question = (Question) o;
      return Objects.equals(this.questionId, question.questionId) && Objects.equals(this.survey, question.survey)
          && Objects.equals(this.text, question.text) && Objects.equals(this.answerType, question.answerType);
    }

    @Override
    public int hashCode() {
      return Objects.hash(this.questionId, this.survey, this.text, this.answerType);
    }
    
    @Schema(name = "Question.AnswerType", description = "Question.AnswerType enum")
    public static enum AnswerType {
        SINGLE, ONE, SOME
    }
    
    @Getter
    @Setter
    @Schema(name = "Question.Request", description = "Question.Request entity")
    public static class Request {
        private long surveyId;
        private String text;
        @Schema(description = "AnswerType is enum of SINGLE, ONE, SOME values")
        private String answerType;
    }
    
    @Schema(name = "Question.Response", description = "Question.Response entity")
    public class Response {
        public long getQuestionId() {
            return Question.this.getQuestionId();
        }
        
        public long getSurveyId() throws SurveyServiceException {
            if(Question.this.getSurvey() != null) {
                return Question.this.getSurvey().getSurveyId(); 
            } else {
                throw new SurveyServiceException("Question surveyId is null");
            }
        }
        
        public String getText() {
            return Question.this.getText();
        }
        
        @Schema(description = "AnswerType is enum of SINGLE, ONE, SOME values")
        public String getAnswerType() {
            return Question.this.getAnswerType().name();
        }
    }
}

