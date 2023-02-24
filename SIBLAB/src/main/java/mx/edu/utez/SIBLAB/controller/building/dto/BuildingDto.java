package mx.edu.utez.SIBLAB.controller.building.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import mx.edu.utez.SIBLAB.models.building.Building;
import mx.edu.utez.SIBLAB.models.laboratory.Laboratory;

import javax.validation.constraints.NotEmpty;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
public class BuildingDto {
    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String location;
    private List<Laboratory> laboratories;

    public Building castToBuilding(){
        return new Building(getId(),getName(),getLocation(),null);
    }

    //laboratory
    public Building castToBuildingToLab(){
        return new Building(getId(),getName(),getLocation(),null);
    }
}
