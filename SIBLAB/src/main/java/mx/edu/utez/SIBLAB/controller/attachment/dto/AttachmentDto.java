package mx.edu.utez.SIBLAB.controller.attachment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.SIBLAB.controller.attachment.dto.validations.classroom.ValidClassroom;
import mx.edu.utez.SIBLAB.controller.attachment.dto.validations.status.ValidStatus;
import mx.edu.utez.SIBLAB.controller.report.dtos.validations.date.ValidDate;
import mx.edu.utez.SIBLAB.models.attachment.Attachment;
import mx.edu.utez.SIBLAB.models.report.Report;
import net.bytebuddy.utility.nullability.MaybeNull;
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
public class AttachmentDto {

    private Long id;

    @NotEmpty(message = "Campo requerido")
    private String specific_report;

    @ValidStatus(message = "Estatus inválido")
    @NotEmpty(message = "Campo requerido")
    private String status;

    @NotEmpty(message = "Campo requerido")
    private String name;

    @ValidClassroom(message = "Grupo inválido")
    @NotEmpty(message = "Campo requerido")
    private String group;

    @MaybeNull
    private List<Report> report;

    @ValidDate(message = "Fecha inválida", dateFormat = "dd-MM-yyyy HH:mm:ss")
    private String create_at;

    private static Date register;

    public Attachment castToAttachment(){
        date();
        return new Attachment(null,getSpecific_report(),register,getStatus(),getName(),getGroup(),null);
    }
    public Attachment castToAttachmentToUpdate(){
        date();
        return new Attachment(getId(),getSpecific_report(),register,getStatus(),getName(),getGroup(),null);
    }
    //report
    public Attachment castToAttachToReport(){
        date();
        return new Attachment(getId(),getSpecific_report(),register,getStatus(),getName(),getGroup(),null);
    }

    public void date(){
        DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        sdf.setLenient(false);

        try {
            register = sdf.parse(getCreate_at());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
