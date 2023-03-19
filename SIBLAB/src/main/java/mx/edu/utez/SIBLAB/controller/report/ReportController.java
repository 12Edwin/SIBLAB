package mx.edu.utez.SIBLAB.controller.report;

import mx.edu.utez.SIBLAB.controller.attachment.dto.AttachmentDto;
import mx.edu.utez.SIBLAB.controller.machine.dtos.MachineDto;
import mx.edu.utez.SIBLAB.controller.report.dtos.ReportDto;
import mx.edu.utez.SIBLAB.controller.user.dtos.UserDto;
import mx.edu.utez.SIBLAB.models.report.Report;
import mx.edu.utez.SIBLAB.models.report.ReportRepository;
import mx.edu.utez.SIBLAB.service.report.ReportService;
import mx.edu.utez.SIBLAB.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api-siblab/report")
@CrossOrigin(origins = {"*"})
public class ReportController {
    @Autowired
    private ReportService service;
    @Autowired
    private ReportRepository reportRepository;

    @GetMapping(value = "/", produces = "application/json")
    public @ResponseBody ResponseEntity<CustomResponse<List<Report>>> getAll(){
        List<Report> results = this.service.getAll().getData();
        results = castMany(results);
        return new ResponseEntity<>(new CustomResponse<>(results,false,200,"Ok"),HttpStatus.OK);
    }
    @GetMapping(value = "/{id}", produces = "application/json")
    public @ResponseBody ResponseEntity<CustomResponse<Optional<Report>>> getById(@PathVariable Long id){
        Optional<Report> results = this.service.getById(id).getData();
        if (results != null)
            results = castOne(results);
        return new ResponseEntity<>(new CustomResponse<>(results,false,200,"Ok"),HttpStatus.OK);
    }
    @GetMapping(value = "/student/{id_student}", produces = "application/json")
    public @ResponseBody ResponseEntity<CustomResponse<List<Report>>> getAllByStudent(@PathVariable Long id_student){
        List<Report> results = this.service.getAllByStudent(id_student).getData();
        results = castMany(results);
        return new ResponseEntity<>(new CustomResponse<>(results,false,200,"Ok"),HttpStatus.OK);
    }
    @GetMapping(value = "/teacher/{id_teacher}", produces = "application/json")
    public @ResponseBody ResponseEntity<CustomResponse<List<Report>>> getAllByTeacher(@PathVariable Long id_teacher){
        List<Report> results = this.service.getByAllTeacher(id_teacher).getData();
        results = castMany(results);
        return new ResponseEntity<>(new CustomResponse<>(results,false,200,"Ok"),HttpStatus.OK);
    }
    @GetMapping(value = "/status/{status}", produces = "application/json")
    public @ResponseBody ResponseEntity<CustomResponse<List<Report>>> getAllByStatus(@PathVariable String status){
        List<Report> results = this.service.getAllByStatus(status).getData();
        results = castMany(results);
        return new ResponseEntity<>(new CustomResponse<>(results,false,200,"Ok"),HttpStatus.OK);
    }
    @PostMapping(value = "/", produces = "application/json")
    public @ResponseBody ResponseEntity<CustomResponse<Report>> insert(@RequestBody @Valid ReportDto report){
        return new ResponseEntity<>(this.service.insert(report.castToReport()),HttpStatus.CREATED);
    }
    @PutMapping(value = "/{id}", produces = "application/json")
    public @ResponseBody ResponseEntity<CustomResponse<Boolean>> update(@RequestBody Report report, @PathVariable Long id){
        return new ResponseEntity<>(this.service.changeStatus(id, report.getStatus(), report.getDefect(), report.getAttachment()),HttpStatus.CREATED);
    }

    public Optional<Report> castOne(Optional<Report> result){
        result.get().setMachine(
                new MachineDto(
                        result.get().getMachine().getId(),
                        result.get().getMachine().getName(),
                        result.get().getMachine().getBrand(),
                        result.get().getMachine().getPath_image(),
                        result.get().getMachine().getHard_disk(),
                        result.get().getMachine().getCpu(),
                        result.get().getMachine().getStatus(),
                        result.get().getMachine().getSpecific_features(),
                        result.get().getMachine().getLaboratory(),
                        result.get().getMachine().getReport()
                ).castToMachineToReport()
        );
        result.get().setUser(
                new UserDto(
                        result.get().getUser().getId(),
                        result.get().getUser().getName(),
                        result.get().getUser().getSurname(),
                        result.get().getUser().getEmail(),
                        result.get().getUser().getPassword(),
                        result.get().getUser().getRole(),
                        result.get().getUser().getClassroom(),
                        result.get().getUser().getCode(),
                        result.get().getUser().getStatus(),
                        result.get().getUser().getPeriods(),
                        result.get().getUser().getReports()
                ).castToUserToReport()
        );
        result.get().setAttachment(new AttachmentDto(
                result.get().getAttachment().getId(),
                result.get().getAttachment().getSpecific_report(),
                result.get().getAttachment().getStatus(),
                result.get().getAttachment().getName(),
                result.get().getAttachment().getClassroom(),
                result.get().getAttachment().getReport(),
                result.get().getAttachment().getCreate_at().toString()
        ).castToAttachToReport());
        return result;
    }
    public List<Report> castMany(List<Report> results){
        return results.stream()
                .map(report -> new ReportDto(
                        report.getId(),
                        report.getStatus(),
                        report.getId_teacher(),
                        report.getTime_Register().toString(),
                        report.getTime_Finish().toString(),
                        report.getDefect(),
                        new UserDto(
                                report.getUser().getId(),
                                report.getUser().getName(),
                                report.getUser().getSurname(),
                                report.getUser().getEmail(),
                                report.getUser().getPassword(),
                                report.getUser().getRole(),
                                report.getUser().getClassroom(),
                                report.getUser().getCode(),
                                report.getUser().getStatus(),
                                report.getUser().getPeriods(),
                                report.getUser().getReports()
                        ).castToUserToReport(),
                        new MachineDto(
                                report.getMachine().getId(),
                                report.getMachine().getName(),
                                report.getMachine().getBrand(),
                                report.getMachine().getPath_image(),
                                report.getMachine().getHard_disk(),
                                report.getMachine().getCpu(),
                                report.getMachine().getStatus(),
                                report.getMachine().getSpecific_features(),
                                report.getMachine().getLaboratory(),
                                report.getMachine().getReport()
                        ).castToMachineToReport(),
                        report.getInfo(),
                        new AttachmentDto(
                                report.getAttachment().getId(),
                                report.getAttachment().getSpecific_report(),
                                report.getAttachment().getStatus(),
                                report.getAttachment().getName(),
                                report.getAttachment().getClassroom(),
                                report.getAttachment().getReport(),
                                report.getAttachment().getCreate_at().toString()
                        ).castToAttachToReport()

                ).castToReportToGet()).collect(Collectors.toList());
    }

}
