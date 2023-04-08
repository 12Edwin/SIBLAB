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

    private Classroom classrooms_id;

    public Period castToPeriod(){
        return new Period(null,getSemester(),getUser_id(),getClassrooms_id());
    }
    public Period castToPeriodToUpdate(){
        return new Period(getId(),getSemester(),getUser_id(),getClassrooms_id());
    }

    //user
    public Period castToPeriodToUser(){
        Semester semester1 = new Semester();
        if (getSemester() != null) {
            semester1.setId(getSemester().getId());
            semester1.setName(getSemester().getName());
            semester1.setSemester_start(getSemester().getSemester_start());
            semester1.setSemester_finish(getSemester().getSemester_finish());
        }
        Classroom classroom = new Classroom();
        if (getClassrooms_id() != null){
            classroom.setId(getClassrooms_id().getId());
            classroom.setName(getClassrooms_id().getName());
            classroom.setGrade(getClassrooms_id().getGrade());
            classroom.setCareer(getClassrooms_id().getCareer());
            classroom.setTotal_students(getClassrooms_id().getTotal_students());
        }

        return new Period(getId(),semester1,null,classroom);
    }
    //classroom
    public Period castToPeriodToClass(){
        User user1= new User();
        if (getUser_id() != null) {
            user1.setId(getUser_id().getId());
            user1.setEmail(getUser_id().getEmail());
            user1.setRole(getUser_id().getRole());
            user1.setStatus(getUser_id().getStatus());
            user1.setName(getClassrooms_id().getName());
            user1.setSurname(getUser_id().getSurname());
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
        Classroom classroom = new Classroom();
        if (getClassrooms_id() != null){
            classroom.setId(getClassrooms_id().getId());
            classroom.setName(getClassrooms_id().getName());
            classroom.setGrade(getClassrooms_id().getGrade());
            classroom.setCareer(getClassrooms_id().getCareer());
            classroom.setTotal_students(getClassrooms_id().getTotal_students());
        }
        return new Period(getId(),null,user,classroom);
    }
}
