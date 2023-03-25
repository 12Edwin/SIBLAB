package mx.edu.utez.SIBLAB.models.semester;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.SIBLAB.models.period.Period;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="Semesters")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Semester {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Date semester_start;

    @Column(nullable = false)
    private Date semester_finish;

    //Relationship with Period
    @OneToMany(mappedBy = "semester", fetch = FetchType.LAZY)
    private List<Period> periods;
}
