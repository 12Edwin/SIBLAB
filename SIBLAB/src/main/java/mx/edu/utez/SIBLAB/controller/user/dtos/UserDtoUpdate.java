package mx.edu.utez.SIBLAB.controller.user.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.SIBLAB.controller.user.dtos.validations.classroom.ValidClassroom;
import mx.edu.utez.SIBLAB.controller.user.dtos.validations.code.ValidCode;
import mx.edu.utez.SIBLAB.controller.user.dtos.validations.email.ValidEmail;
import mx.edu.utez.SIBLAB.controller.user.dtos.validations.noNumbers.ValidNoNumbers;
import mx.edu.utez.SIBLAB.controller.user.dtos.validations.role.ValidRole;
import mx.edu.utez.SIBLAB.controller.user.dtos.validations.teacher.ValidTeacher;
import mx.edu.utez.SIBLAB.models.user.User;
import net.bytebuddy.utility.nullability.MaybeNull;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDtoUpdate {
    private Long id;

    @MaybeNull
    @ValidNoNumbers(message = "Este campo no acepta valores numéricos")
    private String name;

    @MaybeNull
    @ValidNoNumbers(message = "Este campo no acepta valores numéricos")
    private String surname;



    @MaybeNull
    @Length(min = 6, message = "Su clave debe contener más de 6 caracteres")
    private String password;

    @MaybeNull
    @ValidRole(message = "Rol inválido")
    private String role;

    @MaybeNull
    @ValidNoNumbers(message = "Este campo no acepta valores numéricos")
    @Length(min = 2, max = 10, message = "EL nombre de la carrera debe ser de entre 2 y 10 caracteres")
    private String career;

    @MaybeNull
    @ValidNoNumbers(message = "Este campo no acepta valores numéricos")
    @Length(min = 4, max = 10, message = "EL nombre de la división debe ser de entre 4 y 10 caracteres")
    private String division;

    @MaybeNull
    @ValidClassroom(message = "Grupo no encotrado")
    private String classroom;



    @MaybeNull
    @ValidTeacher(message = "Profesor no válido")
    private User id_teacher;
    public User castToUserForUpdate(){
        return new User(getId(),getName(),getSurname(),null,getPassword(),getRole(),true,getCareer(),getDivision(),getClassroom(),null,getId_teacher(),null );
    }
}
