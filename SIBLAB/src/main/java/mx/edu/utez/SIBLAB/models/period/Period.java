package mx.edu.utez.SIBLAB.models.period;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.SIBLAB.models.classroom.Classroom;
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

    @Column(nullable = false)
    private String semester;

    @Column(nullable = false)
    private Date start_semester;

    @Column(nullable = false)
    private Date finish_semester;

    //Relationship with user
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user",nullable = false)
    @JsonIgnore
    private User user;

    //RelationShip with classroom
    @OneToMany(mappedBy = "period",fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Classroom> classrooms;
}
