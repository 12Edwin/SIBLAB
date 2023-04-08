package mx.edu.utez.SIBLAB.controller.classroom.dtos;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.SIBLAB.controller.classroom.dtos.validations.period.ValidPeriod;
import mx.edu.utez.SIBLAB.models.classroom.Classroom;
import mx.edu.utez.SIBLAB.models.period.Period;
import mx.edu.utez.SIBLAB.models.user.User;
import net.bytebuddy.utility.nullability.MaybeNull;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClassroomDto {
    private Long id;

    @NotEmpty(message = "Campo requerido")
    @Length(min = 1, max = 1, message = "El grupo solo debe contener un caracter")
    private String name;

    @MaybeNull
    private int total_students;

    @NotEmpty(message = "Campo requerido")
    private String career;

    @NotEmpty(message = "Campo requerido")
    private String grade;

    @MaybeNull
    private List<User> users;

    private List<Period> periods;

    public Classroom castToClassroom (){
        return new Classroom(null,getName(),getCareer(),getGrade(),getTotal_students(),null,null);
    }
    public Classroom castToClassroomToUpdate (){
        return new Classroom(getId(),getName(),getCareer(),getGrade(),getTotal_students(),null,null);
    }

    //Period
    public Classroom castToClassroomToPeriod(){
        return new Classroom(getId(),getName(),getCareer(),getGrade(),getTotal_students(),null,null);
    }

    //user
    public Classroom castToClassroomToUser(){
        return new Classroom(getId(),getName(),getCareer(),getGrade(),getTotal_students(),null,null);
    }
}
