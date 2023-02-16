package mx.edu.utez.SIBLAB.models.report;

import jdk.jfr.Timestamp;
import lombok.*;
import mx.edu.utez.SIBLAB.models.machine.Machine;
import mx.edu.utez.SIBLAB.models.user.User;
import net.bytebuddy.utility.dispatcher.JavaDispatcher;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reports")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;
    private Long id_teacher;
    @Column(nullable = false)
    private Date time_Register;
    private Date time_Finish;
    private String info;
    //Relationship with users
    @ManyToOne
    @JoinColumn(name = "id_user",nullable = false)
    private User user;

    //Relationship with machine
    @OneToOne
    @JoinColumn(name = "id_machine", nullable = false)
    private Machine machine;
}
