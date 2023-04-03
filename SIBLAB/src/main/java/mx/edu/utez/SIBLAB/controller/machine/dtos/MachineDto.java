package mx.edu.utez.SIBLAB.controller.machine.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.SIBLAB.controller.machine.dtos.laboratory.ValidLaboratory;
import mx.edu.utez.SIBLAB.models.building.Building;
import mx.edu.utez.SIBLAB.models.laboratory.Laboratory;
import mx.edu.utez.SIBLAB.models.machine.Machine;
import mx.edu.utez.SIBLAB.models.report.Report;
import net.bytebuddy.utility.nullability.MaybeNull;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MachineDto {
    private Long id;

    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Campo requerido")
    private String brand;

    @MaybeNull
    private String path_image;

    @NotEmpty(message = "Campo requerido")
    private String hard_disk;

    @NotEmpty(message = "Campo requerido")
    private String cpu;

    @MaybeNull
    private Boolean status;

    @NotEmpty(message = "Campo requerido")
    @Length(min = 5, message = "Mínimo 5 caractéres")
    private String specific_features;

    @ValidLaboratory(message = "Laboratorio inválido")
    private Laboratory id_laboratory;

    private List<Report> report;

    public Machine castToMachine(){
        return new Machine(null,getName(),getBrand(),getPath_image(),getHard_disk(),getCpu(),true,getSpecific_features(),null,getId_laboratory());
    }
    public Machine castToMachineToUpdate(){
        return new Machine(getId(),getName(),getBrand(),getPath_image(),getHard_disk(),getCpu(),true,getSpecific_features(),null,getId_laboratory());
    }

    //Report
    public Machine castToMachineToReport(){
        Laboratory lab = new Laboratory();
        if (getId_laboratory() != null) {
            lab.setId(getId_laboratory().getId());
            lab.setName(getId_laboratory().getName());
            lab.setDescription(getId_laboratory().getDescription());
            Building building = new Building();
            building.setId(getId_laboratory().getBuilding().getId());
            lab.setBuilding(building);
        }
        return new Machine(getId(),getName(),getBrand(),getPath_image(),getHard_disk(),getCpu(),getStatus(),getSpecific_features(),null, lab);
    }
    //Laboratory
    public Machine castToMachineToLab(){
        return new Machine(getId(),getName(),getBrand(),getPath_image(),getHard_disk(),getCpu(),getStatus(),getSpecific_features(),null,null);
    }

    //Machine
}
