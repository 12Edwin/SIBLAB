package mx.edu.utez.SIBLAB.controller.laboratory.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.SIBLAB.models.building.Building;
import mx.edu.utez.SIBLAB.models.laboratory.Laboratory;
import mx.edu.utez.SIBLAB.models.machine.Machine;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LaboratoryDto {

    private Long id;

    private String name;

    private String description;

    private Boolean status;

    private List<Machine> machines;

    private Building building;


    //Building
    public Laboratory castToLaboratoryToBuild(){
        return new Laboratory(getId(),getName(),getDescription(),getStatus(),null,null);
    }
    //Machine
    public Laboratory castToLaboratoryToMachine(){
        Building building1 = new Building();
        building1.setId(getBuilding().getId());
        return new Laboratory(getId(),getName(),getDescription(), getStatus(),null,building1);
    }
}
