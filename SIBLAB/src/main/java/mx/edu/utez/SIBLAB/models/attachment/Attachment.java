package mx.edu.utez.SIBLAB.models.attachment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.SIBLAB.models.report.Report;

import javax.persistence.*;
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
    private String status;
    //Relationship with report
    @OneToMany(mappedBy = "attachment")
    @JsonIgnore
    private List<Report> report;
}
