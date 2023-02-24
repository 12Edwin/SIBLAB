package mx.edu.utez.SIBLAB.controller.classroom.dtos;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.SIBLAB.models.classroom.Classroom;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClassroomDto {
    private Long id;
    @NotEmpty(message = "Campo requerido")
    private String name;
    @NotNull(message = "Campo requerido")
    private int total_students;

    public Classroom castToClassroom (){
        return new Classroom(getId(),getName(),getTotal_students());
    }
}
