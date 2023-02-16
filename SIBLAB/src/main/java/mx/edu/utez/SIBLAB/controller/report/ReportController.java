package mx.edu.utez.SIBLAB.controller.report;

import mx.edu.utez.SIBLAB.controller.report.dtos.ReportDto;
import mx.edu.utez.SIBLAB.models.report.Report;
import mx.edu.utez.SIBLAB.service.report.ReportService;
import mx.edu.utez.SIBLAB.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api-siblab/report")
@CrossOrigin(origins = {"*"})
public class ReportController {
    @Autowired
    private ReportService service;
    @GetMapping(value = "/", produces = "application/json")
    public @ResponseBody ResponseEntity<CustomResponse<List<Report>>> getAll(){
        return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
    }
    @GetMapping(value = "/{id}", produces = "application/json")
    public @ResponseBody ResponseEntity<CustomResponse<Optional<Report>>> getById(@PathVariable Long id){
        return new ResponseEntity<>(this.service.getById(id),HttpStatus.OK);
    }
    @GetMapping(value = "/student/{id_student}", produces = "application/json")
    public @ResponseBody ResponseEntity<CustomResponse<List<Report>>> getAllByStudent(@PathVariable Long id_student){
        return new ResponseEntity<>(this.service.getAllByStudent(id_student),HttpStatus.OK);
    }
    @GetMapping(value = "/teacher/{id_teacher}", produces = "application/json")
    public @ResponseBody ResponseEntity<CustomResponse<List<Report>>> getAllByTeacher(@PathVariable Long id_teacher){
        return new ResponseEntity<>(this.service.getByAllTeacher(id_teacher),HttpStatus.OK);
    }
    @GetMapping(value = "/status/{status}", produces = "application/json")
    public @ResponseBody ResponseEntity<CustomResponse<List<Report>>> getAllByStatus(@PathVariable String status){
        return new ResponseEntity<>(this.service.getAllByStatus(status),HttpStatus.OK);
    }
    @PostMapping(value = "/", produces = "application/json")
    public @ResponseBody ResponseEntity<CustomResponse<Report>> insert(@RequestBody @Valid ReportDto report){
        return new ResponseEntity<>(this.service.insert(report.castToReport()),HttpStatus.CREATED);
    }
    @PutMapping(value = "/{id}", produces = "application/json")
    public @ResponseBody ResponseEntity<CustomResponse<Boolean>> update(@RequestBody @Valid ReportDto report, @PathVariable Long id){
        return new ResponseEntity<>(this.service.changeStatus(id, report.castToReportUpdate().getStatus()),HttpStatus.CREATED);
    }

}
