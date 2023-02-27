package mx.edu.utez.SIBLAB.models.attachment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;
import mx.edu.utez.SIBLAB.models.report.Report;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "achievements")
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
    private String group;

    //Relationship with report
    @OneToMany(mappedBy = "attachment")
    private List<Report> report;
}
