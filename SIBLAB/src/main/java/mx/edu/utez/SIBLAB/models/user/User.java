package mx.edu.utez.SIBLAB.models.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import mx.edu.utez.SIBLAB.models.report.Report;

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

    private String career;
    private String division;
    private String classroom;
    @Column(unique = true)
    private String code;

    //Recursive relationship
    @ManyToOne
    @JoinColumn(referencedColumnName = "id",name = "id_teacher")
    @JsonBackReference
    private User user;

    //Relationship with report
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private List<Report> reports;
}
