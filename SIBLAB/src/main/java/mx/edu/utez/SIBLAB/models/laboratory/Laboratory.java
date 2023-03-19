package mx.edu.utez.SIBLAB.models.laboratory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.SIBLAB.models.building.Building;
import mx.edu.utez.SIBLAB.models.machine.Machine;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "laboratories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Laboratory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Boolean status;

    //Relationship with machine
    @OneToMany(mappedBy = "laboratory")
    private List<Machine> machines;

    //Relationship with building
    @ManyToOne
    @JoinColumn(name = "id_building")
    private Building building;
}
