package mx.edu.utez.SIBLAB.controller.machine.dtos;

import jdk.jfr.BooleanFlag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.SIBLAB.controller.machine.dtos.laboratory.ValidLaboratory;
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

    @NotEmpty(message = "Campo requerido")
    @ValidLaboratory(message = "Laboratorio inválido")
    private Laboratory id_laboratory;

    private List<Report> report;

    public Machine castToMachine(){
        return new Machine(null,getBrand(),getPath_image(),getHard_disk(),getCpu(),true,getSpecific_features(),null,getId_laboratory());
    }
    public Machine castToMachineUpdate(){
        return new Machine(null,getBrand(),getPath_image(),getHard_disk(),getCpu(),getStatus(),getSpecific_features(),null,getId_laboratory());
    }
    public Machine castToMachineDelete(){
        return new Machine(null,null,null,null,null,getStatus(),null,null,null);
    }

    //Report
    public Machine castToMachineToReport(){
        Laboratory lab = new Laboratory();
        lab.setId(getId_laboratory().getId());
        return new Machine(getId(),getBrand(),getPath_image(),getHard_disk(),getCpu(),getStatus(),getSpecific_features(),null, lab);
    }
    //Laboratory
    public Machine castToMachineToLab(){
        return new Machine(getId(),getBrand(),getPath_image(),getHard_disk(),getCpu(),getStatus(),getSpecific_features(),null,null);
    }

    //Machine
}
