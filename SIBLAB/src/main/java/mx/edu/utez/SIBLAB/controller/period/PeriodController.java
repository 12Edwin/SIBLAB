package mx.edu.utez.SIBLAB.controller.period;

import mx.edu.utez.SIBLAB.controller.classroom.dtos.ClassroomDto;
import mx.edu.utez.SIBLAB.controller.period.dto.PeriodDto;
import mx.edu.utez.SIBLAB.controller.semester.dto.SemesterDto;
import mx.edu.utez.SIBLAB.controller.user.dtos.UserDto;
import mx.edu.utez.SIBLAB.models.period.Period;
import mx.edu.utez.SIBLAB.service.period.PeriodService;
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
@CrossOrigin(origins = {"*"})
@RequestMapping(value = "/api-siblab/period")
public class PeriodController {
    @Autowired
    private PeriodService service;

    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<CustomResponse<List<Period>>> getAll(){
    List<Period> periods = this.service.getAll().getData();
    periods = castToMany(periods);
    return new ResponseEntity<>(new CustomResponse<>(periods,false,200,"Ok"),HttpStatus.OK);
    }
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<CustomResponse<Optional<Period>>> getById(@PathVariable Long id){
        Optional<Period> period = this.service.getById(id).getData();
        if (period != null)
            period = castToOne(period);
        return new ResponseEntity<>(new CustomResponse<>(period,false,200,"Ok"),HttpStatus.OK);    }
    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<CustomResponse<Period>> insert(@RequestBody @Valid PeriodDto period){
        return new ResponseEntity<>(this.service.insert(period.castToPeriod()), HttpStatus.CREATED);
    }
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<CustomResponse<Optional<Period>>> update(@RequestBody @Valid PeriodDto period, @PathVariable Long id){
        period.setId(id);
        Optional<Period> result = Optional.ofNullable(this.service.update(period.castToPeriodToUpdate()).getData());
        if (result != null)
            result = castToOne(result);
        return new ResponseEntity<>(new CustomResponse<>(result,false,200,"Ok"),HttpStatus.CREATED );
    }

    public Optional<Period> castToOne(Optional<Period> period){
        if (period.get().getClassrooms() != null) {
            period.get().setClassrooms(period.get().getClassrooms().stream().map(
                    classroom -> new ClassroomDto(
                            classroom.getId(),
                            classroom.getName(),
                            classroom.getTotal_students(),
                            classroom.getCareer(),
                            classroom.getGrade(),
                            classroom.getUsers(),
                            classroom.getPeriod()).castToClassroomToPeriod()
            ).collect(Collectors.toList()));
        }
        if (period.get().getUser() != null) {
            period.get().setUser(new UserDto(
                    period.get().getUser().getId(),
                    period.get().getUser().getName(),
                    period.get().getUser().getSurname(),
                    period.get().getUser().getEmail(),
                    period.get().getUser().getPassword(),
                    period.get().getUser().getRole(),
                    period.get().getUser().getClassroom(),
                    period.get().getUser().getCode(),
                    period.get().getUser().getStatus(),
                    period.get().getUser().getPeriods(),
                    period.get().getUser().getReports()
            ).castToUserToPeriod());
        }
        if (period.get().getSemester() != null){
            period.get().setSemester(new SemesterDto(
                    period.get().getSemester().getId(),
                    period.get().getSemester().getName(),
                    period.get().getSemester().getSemester_start().toString().split(" ")[0].split("-")[2] + "-" +
                            period.get().getSemester().getSemester_start().toString().split(" ")[0].split("-")[1] + "-" +
                            period.get().getSemester().getSemester_start().toString().split(" ")[0].split("-")[0],

                    period.get().getSemester().getSemester_finish().toString().split(" ")[0].split("-")[2] + "-" +
                            period.get().getSemester().getSemester_finish().toString().split(" ")[0].split("-")[1] + "-" +
                            period.get().getSemester().getSemester_finish().toString().split(" ")[0].split("-")[0],
                    period.get().getSemester().getPeriods()
            ).castToSemesterToPeriod());
        }
        return period;
    }
    public List<Period> castToMany(List<Period> periods){
        return periods.stream().map(
                period -> new Period(
                        period.getId(),
                        new SemesterDto(
                                period.getSemester().getId(),
                                period.getSemester().getName(),
                                period.getSemester().getSemester_start().toString().split(" ")[0].split("-")[0] + "-" +
                                        period.getSemester().getSemester_start().toString().split(" ")[0].split("-")[1] + "-" +
                                        period.getSemester().getSemester_start().toString().split(" ")[0].split("-")[2],

                                period.getSemester().getSemester_finish().toString().split(" ")[0].split("-")[0] + "-" +
                                        period.getSemester().getSemester_finish().toString().split(" ")[0].split("-")[1] + "-" +
                                        period.getSemester().getSemester_finish().toString().split(" ")[0].split("-")[2],
                                period.getSemester().getPeriods()
                        ).castToSemesterToPeriod(),
                        new UserDto(
                                period.getUser().getId(),
                                period.getUser().getName(),
                                period.getUser().getSurname(),
                                period.getUser().getEmail(),
                                period.getUser().getPassword(),
                                period.getUser().getRole(),
                                period.getUser().getClassroom(),
                                period.getUser().getCode(),
                                period.getUser().getStatus(),
                                period.getUser().getPeriods(),
                                period.getUser().getReports()
                        ).castToUserToPeriod(),
                        period.getClassrooms().stream().map(
                                classroom -> new ClassroomDto(
                                        classroom.getId(),
                                        classroom.getName(),
                                        classroom.getTotal_students(),
                                        classroom.getCareer(),
                                        classroom.getGrade(),
                                        classroom.getUsers(),
                                        classroom.getPeriod()
                                ).castToClassroomToPeriod()
                        ).collect(Collectors.toList())
                )
        ).collect(Collectors.toList());
    }
}
