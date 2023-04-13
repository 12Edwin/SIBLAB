package mx.edu.utez.SIBLAB.controller.building.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.SIBLAB.models.building.Building;
import mx.edu.utez.SIBLAB.models.laboratory.Laboratory;
import net.bytebuddy.utility.nullability.MaybeNull;

import javax.validation.constraints.NotEmpty;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BuildingDto {
    private Long id;

    @NotEmpty(message = "Campo requerido")
    private String name;
    @NotEmpty(message = "Campo requerido")
    private String location;


    private List<Laboratory> laboratories;

    public Building castToBuilding(){
        return new Building(null,getName(),getLocation(),null);
    }

    public Building castToBuildingToUpdate(){
        return new Building(getId(),getName(),getLocation(),null);
    }

    //laboratory
    public Building castToBuildingToLab(){
        return new Building(getId(),getName(),getLocation(),null);
    }
}
