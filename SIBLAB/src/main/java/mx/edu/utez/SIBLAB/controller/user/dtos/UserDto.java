package mx.edu.utez.SIBLAB.controller.user.dtos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.Nullable;
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
import mx.edu.utez.SIBLAB.models.report.Report;
import mx.edu.utez.SIBLAB.models.user.User;
import net.bytebuddy.utility.nullability.MaybeNull;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserDto {
    private Long id;

    @ValidNoNumbers(message = "Este campo no acepta valores numéricos")
    @NotEmpty(message = "El nombre es requerido")
    private String name;

    @ValidNoNumbers(message = "Este campo no acepta valores numéricos")
    @NotEmpty(message = "El apellido es requerido")
    private String surname;

    @NotEmpty(message = "El correo es obligatorio")
    @ValidEmail(message = "Correo inválido")
    private String email;

    @NotEmpty(message = "Este campo es obligatorio")
    @Length(min = 6, message = "Su clave debe contener más de 6 caracteres")
    private String password;

    @NotEmpty(message = "El rol es obligatorio")
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
    @ValidCode(message = "matrícula ya registrada")
    @Length(min = 10,message = "EL código debe ser de 10 caracteres")
    private String code;

    @MaybeNull
    @ValidTeacher(message = "Profesor no válido")
    private User id_teacher;
    public User castToUser(){
        return new User(getId(),getName(),getSurname(),getEmail(),getPassword(),getRole(),true,getCareer(),getDivision(),getClassroom(),getCode(),getId_teacher(),null );
    }
}
