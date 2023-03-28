package mx.edu.utez.SIBLAB.controller.period.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.SIBLAB.controller.period.dto.validations.student.ValidUser;
import mx.edu.utez.SIBLAB.models.classroom.Classroom;
import mx.edu.utez.SIBLAB.models.period.Period;
import mx.edu.utez.SIBLAB.models.semester.Semester;
import mx.edu.utez.SIBLAB.models.user.User;
import net.bytebuddy.utility.nullability.MaybeNull;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PeriodDto {

    private Long id;

    private Semester semester;

    @ValidUser(message = "Profesor inválido")
    private User user_id;

    @MaybeNull
    private List<Classroom> classrooms;

    public Period castToPeriod(){
        return new Period(null,getSemester(),getUser_id(),null);
    }
    public Period castToPeriodToUpdate(){
        return new Period(getId(),getSemester(),getUser_id(),null);
    }

    //user
    public Period castToPeriodToUser(){
        Semester semester1 = new Semester();
        if (semester1 != null) {
            semester1.setId(getSemester().getId());
            semester1.setName(getSemester().getName());
            semester1.setSemester_start(getSemester().getSemester_start());
            semester1.setSemester_finish(getSemester().getSemester_finish());
        }

        return new Period(getId(),semester1,null,null);
    }
    //classroom
    public Period castToPeriodToClass(){
        User user1= new User();
        if (getUser_id() != null) {
            user1.setId(getUser_id().getId());
        }
        Semester semester1 = new Semester();
        if (semester1 != null) {
            semester1.setId(getSemester().getId());
            semester1.setName(getSemester().getName());
            semester1.setSemester_start(getSemester().getSemester_start());
            semester1.setSemester_finish(getSemester().getSemester_finish());
        }

        return new Period(getId(),semester1,user1,null);
    }

    //Semester
    public Period castToPeriodToSemester(){
        User user = new User();
        if (getUser_id() != null){
            user.setId(getUser_id().getId());
        }
        return new Period(getId(),null,user,null);
    }
}
