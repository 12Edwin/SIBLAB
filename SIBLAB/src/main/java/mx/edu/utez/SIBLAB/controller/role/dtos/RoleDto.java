package mx.edu.utez.SIBLAB.controller.role.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.SIBLAB.models.role.Role;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoleDto {
    private Long id;
    @NotEmpty(message = "Campo requerido")
    private String name;
    @NotEmpty(message = "Campo requerido")
    private String description;

    public Role castToRole(){
        return new Role(null,getName(),getDescription());
    }
    public Role castToRoleToUpdate(){
        return new Role(getId(),getName(),getDescription());
    }
}
