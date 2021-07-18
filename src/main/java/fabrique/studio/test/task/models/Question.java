package fabrique.studio.test.task.models;

import java.util.Objects;

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
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "question")
@Getter
@Setter
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long question_id;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "survey_id")
    private Survey survey;
    private String text;
    
    @Enumerated(EnumType.STRING)
    private AnswerType answerType;
    
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
      return Objects.equals(this.question_id, question.question_id) && Objects.equals(this.survey, question.survey)
          && Objects.equals(this.text, question.text) && Objects.equals(this.answerType, question.answerType);
    }

    @Override
    public int hashCode() {
      return Objects.hash(this.question_id, this.survey, this.text, this.answerType);
    }
    
    public static enum AnswerType {
        SINGLE, ONE, SOME
    }
    
    @Getter
    @Setter
    public static class QuestionRequest {
        private long surveyId;
        private String text;
        private String answerType;
    }
}

