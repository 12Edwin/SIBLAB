package mx.edu.utez.SIBLAB.controller.machine.dtos;

import jdk.jfr.BooleanFlag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.SIBLAB.models.machine.Machine;
import net.bytebuddy.utility.nullability.MaybeNull;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

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

    @NotBlank(message = "Campo requerido")
    private String model;

    private Boolean status;

    @Length(min = 5, message = "Mínimo 5 caractéres")
    private String specific_features;

    public Machine castToMachine(){
        return new Machine(null,getBrand(),getPath_image(),getModel(),true,getSpecific_features(),null);
    }
    public Machine castToMachineUpdate(){
        return new Machine(null,getBrand(),getPath_image(),getModel(),getStatus(),getSpecific_features(),null);
    }
    public Machine castToMachineDelete(){
        return new Machine(null,null,null,null,getStatus(),null,null);
    }
}
