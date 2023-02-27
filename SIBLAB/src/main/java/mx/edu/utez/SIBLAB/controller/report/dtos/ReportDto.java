package mx.edu.utez.SIBLAB.controller.report.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jdk.jfr.Timestamp;
import lombok.*;
import mx.edu.utez.SIBLAB.controller.report.dtos.validations.attachment.ValidAttachment;
import mx.edu.utez.SIBLAB.controller.report.dtos.validations.date.ValidDate;
import mx.edu.utez.SIBLAB.controller.report.dtos.validations.machine.ValidMachine;
import mx.edu.utez.SIBLAB.controller.report.dtos.validations.status.ValidStatus;
import mx.edu.utez.SIBLAB.controller.report.dtos.validations.student.ValidStudent;
import mx.edu.utez.SIBLAB.controller.report.dtos.validations.teacher.ValidTeacher;
import mx.edu.utez.SIBLAB.models.attachment.Attachment;
import mx.edu.utez.SIBLAB.models.machine.Machine;
import mx.edu.utez.SIBLAB.models.report.Report;
import mx.edu.utez.SIBLAB.models.user.User;
import net.bytebuddy.utility.nullability.MaybeNull;
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

    @MaybeNull
    private Boolean defect;

    @ValidStudent(message = "Estudiante inválido") //Sacar id y validar en tabla users
    private User student;

    @ValidMachine(message = "Máquina inválida") //Sacar id y validar en tabla machines
    private Machine machine;

    @NotEmpty(message = "Campo requerido")
    @Length(min = 10, message = "Escriba un mínimo de 10 caracteres")
    private String info;

    @MaybeNull
    @ValidAttachment(message = "Reporte docente inválido")
    private Attachment attachment;

    static Date register;
    static Date finish;
    public Report castToReport(){
        date();
        return new Report(null,"Pending_student",getId_teacher(),register,finish,getInfo(),null,getStudent(),getMachine(),getAttachment());
    }

    //Machine
    public Report castToReportToMachine(){
        date();
        return new Report(getId(),getStatus(),getId_teacher(),register,finish,getInfo(),getDefect(),null,null,null);
    }
    //User
    public Report castToReportToUser(){
        Machine machine1 = new Machine();
        machine1.setId(getMachine().getId());
        Attachment attachment1 = new Attachment();
        attachment1.setId(getAttachment().getId());
        date();
        return new Report(getId(),getStatus(),getId_teacher(),register,finish,getInfo(),getDefect(),null,machine1,attachment1);
    }
    //Attachment
    public Report castToReportToAttach(){
        User user = new User();
        user.setId(getStudent().getId());
        Machine machine1 = new Machine();
        machine1.setId(getMachine().getId());
        date();
        return new Report(getId(),getStatus(),getId_teacher(),register,finish,getInfo(),getDefect(),user,machine1,null);
    }

    public void date(){
        DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        sdf.setLenient(false);

        try {
            register = sdf.parse(getTime_Register());
            finish = sdf.parse(getTime_Register());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
