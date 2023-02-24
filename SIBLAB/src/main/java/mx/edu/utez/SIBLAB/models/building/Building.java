package mx.edu.utez.SIBLAB.models.building;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.SIBLAB.models.laboratory.Laboratory;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "buildings")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;

    //RelationShip with laboratory
    @OneToMany(mappedBy = "building")
    @JsonIgnore
    private List<Laboratory> laboratories;
}
