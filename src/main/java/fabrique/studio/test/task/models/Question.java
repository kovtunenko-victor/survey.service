package fabrique.studio.test.task.models;

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
    private Long id;
    private String text;
    
    @Enumerated(EnumType.STRING)
    private AnswerType answerType;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private Survey survey;
    
    public Question() {
       
    }
    
    public Question(String text, Question.AnswerType answerType, Survey survey) {
        this.text = text;
        this.answerType = answerType;
        this.survey = survey;
    }
    
    public static enum AnswerType {
        SINGLE, ONE, SOME
    }
}

