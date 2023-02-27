package mx.edu.utez.SIBLAB.models.report;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.jfr.Timestamp;
import lombok.*;
import mx.edu.utez.SIBLAB.models.attachment.Attachment;
import mx.edu.utez.SIBLAB.models.machine.Machine;
import mx.edu.utez.SIBLAB.models.user.User;
import net.bytebuddy.utility.dispatcher.JavaDispatcher;
import net.bytebuddy.utility.nullability.MaybeNull;
import org.springframework.beans.factory.annotation.Value;

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

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private Long id_teacher;

    @Column(nullable = false)
    private Date time_Register;

    @MaybeNull
    private Date time_Finish;

    @MaybeNull
    private String info;

    @Column(nullable = false)
    private Boolean defect;

    //Relationship with users
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user",nullable = false)
    private User user;

    //Relationship with machine
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_machine", nullable = false)
    private Machine machine;

    //Relationship with attachment
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attachment_id",nullable = false)
    private Attachment attachment;
}
