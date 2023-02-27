package mx.edu.utez.SIBLAB.models.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import mx.edu.utez.SIBLAB.models.classroom.Classroom;
import mx.edu.utez.SIBLAB.models.machine.Machine;
import mx.edu.utez.SIBLAB.models.period.Period;
import mx.edu.utez.SIBLAB.models.report.Report;
import net.bytebuddy.utility.nullability.MaybeNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private Boolean status;

    //relationship with classroom
    @MaybeNull
    @ManyToOne()
    @JoinColumn(name = "id_classroom")
    private Classroom classroom;

    @Column(unique = true)
    private String code;

    //Relationship with report
    @OneToMany(mappedBy = "user")
    private List<Report> reports;

    //Relationship with period
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private List<Period> periods;
}
