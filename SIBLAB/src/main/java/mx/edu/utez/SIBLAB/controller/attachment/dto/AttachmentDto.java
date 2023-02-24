package mx.edu.utez.SIBLAB.controller.attachment.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.SIBLAB.models.attachment.Attachment;
import mx.edu.utez.SIBLAB.models.report.Report;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AttachmentDto {

    private Long id;

    private String specific_report;

    private String status;

    private List<Report> report;

    //report
    public Attachment castToAttachToReport(){
        return new Attachment(getId(),getSpecific_report(),getStatus(),null);
    }
}
