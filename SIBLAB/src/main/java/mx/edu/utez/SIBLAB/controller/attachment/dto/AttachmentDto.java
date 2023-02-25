package mx.edu.utez.SIBLAB.controller.attachment.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.SIBLAB.controller.attachment.dto.validations.classroom.ValidClassroom;
import mx.edu.utez.SIBLAB.controller.attachment.dto.validations.status.ValidStatus;
import mx.edu.utez.SIBLAB.models.attachment.Attachment;
import mx.edu.utez.SIBLAB.models.report.Report;
import net.bytebuddy.utility.nullability.MaybeNull;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
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


    //report
    public Attachment castToAttachToReport(){
        return new Attachment(getId(),getSpecific_report(),getStatus(),getName(),getGroup(),null);
    }
}
