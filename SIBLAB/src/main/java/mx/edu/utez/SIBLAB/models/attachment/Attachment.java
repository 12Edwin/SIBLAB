package mx.edu.utez.SIBLAB.models.attachment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.SIBLAB.models.report.Report;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
@Entity
@Table(name = "attachments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String specific_report;

    @Column(nullable = false)
    private Date create_at;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String classroom;

    private String email;

    //Relationship with report
    @OneToMany(mappedBy = "attachment")
    private List<Report> report;
}
