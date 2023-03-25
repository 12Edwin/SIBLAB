package mx.edu.utez.SIBLAB.controller.semester;

import mx.edu.utez.SIBLAB.controller.period.dto.PeriodDto;
import mx.edu.utez.SIBLAB.controller.role.dtos.RoleDto;
import mx.edu.utez.SIBLAB.controller.semester.dto.SemesterDto;
import mx.edu.utez.SIBLAB.models.period.Period;
import mx.edu.utez.SIBLAB.models.role.Role;
import mx.edu.utez.SIBLAB.models.semester.Semester;
import mx.edu.utez.SIBLAB.service.semester.SemesterService;
import mx.edu.utez.SIBLAB.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api-siblab/semester")
@CrossOrigin(origins = {"*"})
public class SemesterController {

    @Autowired
    private SemesterService service;

    @GetMapping(value = "/", produces = "application/json")
    public @ResponseBody ResponseEntity<CustomResponse<List<Semester>>> getAll(){
        List<Semester> semesters = this.service.getAll().getData();
        semesters = castToMany(semesters);
        return new ResponseEntity<>(new CustomResponse<>(semesters,false,200,"Ok"),HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public @ResponseBody ResponseEntity<CustomResponse<Optional<Semester>>> getById(@PathVariable Long id){
        Optional<Semester> semester = this.service.getById(id).getData();
        if (semester != null)
            semester = castToOne(semester);
        return new ResponseEntity<>(new CustomResponse<>(semester,false,200,"Ok"),HttpStatus.OK);
    }

    @PostMapping(value = "/", produces = "application/json")
    public @ResponseBody ResponseEntity<CustomResponse<Semester>> insert(@RequestBody @Valid SemesterDto semester){
        return new ResponseEntity<>(this.service.insert(semester.castToSave()), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}",produces = "application/json")
    public @ResponseBody ResponseEntity<CustomResponse<Semester>> update(@PathVariable Long id, @RequestBody @Valid SemesterDto semester){
        semester.setId(id);
        return new ResponseEntity<>(this.service.update(semester.castToUpdate()),HttpStatus.CREATED);
    }

    public Optional<Semester> castToOne(Optional<Semester> semester){
        if (semester.get().getPeriods() != null) {
            semester.get().setPeriods(semester.get().getPeriods().stream().map(period -> new PeriodDto(
                            period.getId(),
                            period.getSemester(),
                            period.getUser(),
                            period.getClassrooms()
                    ).castToPeriodToSemester()
            ).collect(Collectors.toList()));
        }
        return semester;
    }

    public List<Semester> castToMany (List<Semester> semesters){
        return semesters.stream().map(semester -> new Semester(
                semester.getId(),
                semester.getName(),
                semester.getSemester_start(),
                semester.getSemester_finish(),
                semester.getPeriods() != null ? semester.getPeriods().stream().map(period -> new PeriodDto(
                        period.getId(),
                        period.getSemester(),
                        period.getUser(),
                        period.getClassrooms()
                ).castToPeriodToSemester()).collect(Collectors.toList()) : null
        )).collect(Collectors.toList());
    }
}
