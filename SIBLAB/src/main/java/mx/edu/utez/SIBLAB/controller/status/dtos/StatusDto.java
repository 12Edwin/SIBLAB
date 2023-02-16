package mx.edu.utez.SIBLAB.controller.status.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.SIBLAB.models.status.Status;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StatusDto {
    private Long id;
    @NotEmpty(message = "Campo requerido")
    private String name;
    @NotEmpty(message = "Campo requerido")
    private String description;
    public Status castToStatus(){
        return new Status(getId(),getName(),getDescription());
    }
}
