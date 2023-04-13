package mx.edu.utez.SIBLAB.controller.laboratory.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.SIBLAB.controller.laboratory.dto.validations.building.ValidBuilding;
import mx.edu.utez.SIBLAB.models.building.Building;
import mx.edu.utez.SIBLAB.models.laboratory.Laboratory;
import mx.edu.utez.SIBLAB.models.machine.Machine;
import net.bytebuddy.utility.nullability.MaybeNull;
import javax.validation.constraints.NotEmpty;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LaboratoryDto {

    private Long id;

    @NotEmpty(message = "Campo requerido")
    private String name;

    @MaybeNull
    private String description;

    @MaybeNull
    private Boolean status;

    @MaybeNull
    private List<Machine> machines;

    @ValidBuilding(message = "Edificio inválido")
    private Building building;

    public Laboratory castToLaboratory(){
        return new Laboratory(null,getName(),getDescription(),true,null,getBuilding());
    }

    public Laboratory castToLaboratoryToUpdate(){
        return new Laboratory(getId(),getName(),getDescription(),getStatus(),null,getBuilding());
    }

    //Building
    public Laboratory castToLaboratoryToBuild(){
        return new Laboratory(getId(),getName(),getDescription(),getStatus(),null,null);
    }
    //Machine
    public Laboratory castToLaboratoryToMachine(){
        Building building1 = new Building();
        if (getBuilding() != null)
            building1.setId(getBuilding().getId());
        return new Laboratory(getId(),getName(),getDescription(), getStatus(),null,building1);
    }
}
