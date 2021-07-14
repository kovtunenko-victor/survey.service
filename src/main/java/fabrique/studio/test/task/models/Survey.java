package fabrique.studio.test.task.models;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
    private Long id;
    private String name;
    private String title;
    private Date dateStart;
    private Date dateEnd;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "survey")
    private Set<Question> questions;
    
    public Survey( ) {
        
    }
    
    public Survey(String name, String title, Date dateStart, Date dateEnd) {
        this.name = name;
        this.title = title;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }
}
