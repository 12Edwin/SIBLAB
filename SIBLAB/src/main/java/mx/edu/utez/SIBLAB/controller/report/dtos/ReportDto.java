package mx.edu.utez.SIBLAB.controller.report.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jdk.jfr.Timestamp;
import lombok.*;
import mx.edu.utez.SIBLAB.controller.report.dtos.validations.date.ValidDate;
import mx.edu.utez.SIBLAB.controller.report.dtos.validations.machine.ValidMachine;
import mx.edu.utez.SIBLAB.controller.report.dtos.validations.status.ValidStatus;
import mx.edu.utez.SIBLAB.controller.report.dtos.validations.student.ValidStudent;
import mx.edu.utez.SIBLAB.controller.report.dtos.validations.teacher.ValidTeacher;
import mx.edu.utez.SIBLAB.models.machine.Machine;
import mx.edu.utez.SIBLAB.models.report.Report;
import mx.edu.utez.SIBLAB.models.user.User;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReportDto {
    private Long id;

    @NotEmpty(message = "Campo requerido")
    @ValidStatus(message = "Status inválido")
    private String status;

    @ValidTeacher(message = "Profesor no válido")
    private Long id_teacher;

    @ValidDate(dateFormat = "dd-MM-yyyy HH:mm:ss",message = "Campo inválido")
    private String time_Register;

    @ValidDate(dateFormat = "dd-MM-yyyy HH:mm:ss", message = "Campo inválido")
    private String time_Finish;

    @ValidStudent(message = "Estudiante inválido") //Sacar id y validar en tabla users
    private User student;

    @ValidMachine(message = "Máquina inválida") //Sacar id y validar en tabla machines
    private Machine machine;

    @NotEmpty(message = "Campo requerido")
    @Length(min = 10, message = "Escriba un mínimo de 10 caracteres")
    private String info;
    public Report castToReport(){
        DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        sdf.setLenient(false);
        Date register;
        Date finish;
        try {
            register = sdf.parse(getTime_Register());
            finish = sdf.parse(getTime_Register());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return new Report(getId(),"Pending_student",getId_teacher(),register,finish,getInfo(),getStudent(),getMachine());
    }
    public Report castToReportUpdate(){
        return new Report(null,getStatus(),null,null,null,null,null,null);
    }
}
