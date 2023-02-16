package mx.edu.utez.SIBLAB.models.machine;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.SIBLAB.models.report.Report;
import net.bytebuddy.utility.nullability.MaybeNull;

import javax.persistence.*;

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
    private String brand;
    @MaybeNull
    private String path_image;
    @Column(nullable = false)
    private String model;
    @Column(nullable = false)
    private Boolean status;
    private String specific_features;

    //Relationship with report
    @OneToOne(mappedBy = "machine",cascade = CascadeType.ALL)
    private Report report;
}
