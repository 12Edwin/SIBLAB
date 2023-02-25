package mx.edu.utez.SIBLAB.controller.period.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.SIBLAB.controller.period.dto.validations.student.ValidUser;
import mx.edu.utez.SIBLAB.models.classroom.Classroom;
import mx.edu.utez.SIBLAB.models.period.Period;
import mx.edu.utez.SIBLAB.models.user.User;
import net.bytebuddy.utility.nullability.MaybeNull;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PeriodDto {

    private Long id;

    @NotEmpty(message = "Campo requerido")
    private String semester;

    @NotEmpty(message = "Campo requerido")
    private String start_semester;

    @NotEmpty(message = "Campo requerido")
    private String finish_semester;

    @NotEmpty(message = "Campo requerido")
    @ValidUser(message = "Profesor inválido")
    private User user_id;

    @MaybeNull
    private List<Classroom> classrooms;
    private static Date start;
    private static Date finish;

    public Period castToPeriod(){
        date();
        return new Period(getId(),getSemester(),start,finish,getUser_id(),getClassrooms());
    }

    //user
    public Period castToPeriodToUser(){
        date();
        return new Period(getId(),getSemester(),start,finish,null,null);
    }
    //classroom
    public Period castToPeriodToClass(){
        User user1= new User();
        user1.setId(getUser_id().getId());
        date();
        return new Period(getId(),getSemester(),start,finish,user1,null);
    }
    public void date(){
        DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setLenient(false);
        try {
            start = sdf.parse(getStart_semester());
            finish = sdf.parse(getFinish_semester());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
