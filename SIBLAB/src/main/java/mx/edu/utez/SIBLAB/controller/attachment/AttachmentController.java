package mx.edu.utez.SIBLAB.controller.attachment;

import mx.edu.utez.SIBLAB.controller.attachment.dto.AttachmentDto;
import mx.edu.utez.SIBLAB.controller.report.dtos.ReportDto;
import mx.edu.utez.SIBLAB.models.attachment.Attachment;
import mx.edu.utez.SIBLAB.models.status.Status;
import mx.edu.utez.SIBLAB.models.status.StatusRepository;
import mx.edu.utez.SIBLAB.service.attachment.AttachmentService;
import mx.edu.utez.SIBLAB.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/api-siblab/attachment")
public class AttachmentController {

    @Autowired
    private AttachmentService service;

    @Autowired
    private StatusRepository repository;

    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<CustomResponse<List<Attachment>>> getAll(){
        List<Attachment> attachments= this.service.getAll().getData();
        attachments = castToMany(attachments);
        return new ResponseEntity<>(new CustomResponse<>(attachments,false,200,"Ok"),HttpStatus.OK);
    }
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<CustomResponse<Optional<Attachment>>> getById(@PathVariable Long id){
        Optional<Attachment> attachment= this.service.getById(id).getData();
        attachment = castToOne(attachment);
        return new ResponseEntity<>(new CustomResponse<>(attachment,false,200,"Ok"),HttpStatus.OK);    }
    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<CustomResponse<Attachment>> insert(@RequestBody @Valid AttachmentDto attachment){
        return new ResponseEntity<>(this.service.insert(attachment.castToAttachment()), HttpStatus.CREATED);
    }
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<CustomResponse<Attachment>> update(@RequestBody @Valid AttachmentDto attachment, @PathVariable Long id){
        attachment.setId(id);
        return new ResponseEntity<>(this.service.update(attachment.castToAttachmentToUpdate()),HttpStatus.CREATED );
    }
    @PatchMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> changeStatus(@PathVariable Long id,@RequestBody @Valid Attachment attachment){
        if (attachment.getStatus() == null)
            return new ResponseEntity<>("Estatus erróneo",HttpStatus.BAD_REQUEST);
        attachment.setId(id);
        List<String> values = new ArrayList<>();
        List<Status> statusList = this.repository.findAll();
        for(Status status : statusList){
            values.add(status.getName());
        }
        if(values.contains(attachment.getStatus()))
            return new ResponseEntity<>("Estatus erróneo", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(this.service.changeStatus(attachment),HttpStatus.CREATED);
    }


    public Optional<Attachment> castToOne(Optional<Attachment> attachment){
        attachment.get().setReport(
                attachment.get().getReport().stream().map(
                        report -> new ReportDto(
                                report.getId(),
                                report.getStatus(),
                                report.getId_teacher(),
                                report.getTime_Register().toString(),
                                report.getTime_Finish().toString(),
                                report.getDefect(),
                                report.getUser(),
                                report.getMachine(),
                                report.getInfo(),
                                report.getAttachment()
                        ).castToReportToAttach()
                ).collect(Collectors.toList())
        );
        return attachment;
    }
    public List<Attachment> castToMany(List<Attachment> attachments){
        attachments = attachments.stream().map(
                attachment ->
                        new Attachment(
                                attachment.getId(),
                                attachment.getSpecific_report(),
                                attachment.getCreate_at(),
                                attachment.getStatus(),
                                attachment.getName(),
                                attachment.getGroup(),
                                attachment.getReport().stream().map(
                                        report -> new ReportDto(
                                                report.getId(),
                                                report.getStatus(),
                                                report.getId_teacher(),
                                                report.getTime_Register().toString(),
                                                report.getTime_Finish().toString(),
                                                report.getDefect(),
                                                report.getUser(),
                                                report.getMachine(),
                                                report.getInfo(),
                                                report.getAttachment()
                                        ).castToReportToAttach()
                                ).collect(Collectors.toList())

                        )).collect(Collectors.toList());
        return attachments;
    }
}
