package mx.edu.utez.SIBLAB.models.machine;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.SIBLAB.models.laboratory.Laboratory;
import mx.edu.utez.SIBLAB.models.report.Report;
import mx.edu.utez.SIBLAB.models.user.User;
import net.bytebuddy.utility.nullability.MaybeNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "machines")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Machine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String brand;

    @MaybeNull
    private String path_image;

    @Column(nullable = false)
    private String hard_disk;

    @Column(nullable = false)
    private String cpu;

    @Column(nullable = false)
    private Boolean status;

    @MaybeNull
    private String specific_features;

    //Relationship with report
    @OneToMany(mappedBy = "machine",cascade = CascadeType.ALL)
    private List<Report> report;

    //Relationship with laboratory
    @ManyToOne
    @JoinColumn(name = "id_laboratory", nullable = false)
    private Laboratory laboratory;
}
