package mx.edu.utez.SIBLAB.models.classroom;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.SIBLAB.models.period.Period;
import mx.edu.utez.SIBLAB.models.user.User;
import net.bytebuddy.utility.nullability.MaybeNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "classrooms")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String career;

    @Column(nullable = false)
    private String grade;

    @MaybeNull
    private int total_students;

    //Relationship with user
    @OneToMany(mappedBy = "classroom",fetch = FetchType.LAZY)
    private List<User> users;

    //Relationship with period
    @OneToMany(mappedBy = "classroom",fetch = FetchType.LAZY)
    private List<Period> period;
}
