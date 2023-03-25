package mx.edu.utez.SIBLAB.models.period;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.SIBLAB.models.classroom.Classroom;
import mx.edu.utez.SIBLAB.models.semester.Semester;
import mx.edu.utez.SIBLAB.models.user.User;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "periods")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Period {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Relationship with semester
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_semester")
    private Semester semester;

    //Relationship with user
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user",nullable = false)
    private User user;

    //RelationShip with classroom
    @OneToMany(mappedBy = "period",fetch = FetchType.LAZY)
    private List<Classroom> classrooms;
}
