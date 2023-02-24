package mx.edu.utez.SIBLAB.controller.attachment;

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
        return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
    }
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<CustomResponse<Optional<Attachment>>> getById(@PathVariable Long id){
        return new ResponseEntity<>(this.service.getById(id),HttpStatus.OK);
    }
    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<CustomResponse<Attachment>> insert(@RequestBody @Valid Attachment attachment){
        return new ResponseEntity<>(this.service.insert(attachment), HttpStatus.CREATED);
    }
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<CustomResponse<Attachment>> update(@RequestBody @Valid Attachment attachment, @PathVariable Long id){
        attachment.setId(id);
        return new ResponseEntity<>(this.service.update(attachment),HttpStatus.CREATED );
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

    public List<Attachment> castToMany(List<Attachment> attachments){
        attachments = attachments.stream().map(
                attachment ->
                        new Attachment(
                                attachment.getId(),
                                attachment.getSpecific_report(),
                                attachment.getStatus(),
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
