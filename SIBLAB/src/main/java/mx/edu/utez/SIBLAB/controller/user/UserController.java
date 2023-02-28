package mx.edu.utez.SIBLAB.controller.user;

import mx.edu.utez.SIBLAB.controller.classroom.dtos.ClassroomDto;
import mx.edu.utez.SIBLAB.controller.period.dto.PeriodDto;
import mx.edu.utez.SIBLAB.controller.report.dtos.ReportDto;
import mx.edu.utez.SIBLAB.controller.user.dtos.UserDto;
import mx.edu.utez.SIBLAB.models.user.User;
import mx.edu.utez.SIBLAB.service.user.UserService;
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
@RequestMapping("/api-siblab/user")
@CrossOrigin(origins = {"*"})
public class UserController {
    @Autowired
    private UserService service;
    @GetMapping(value = "/")
    public @ResponseBody ResponseEntity<CustomResponse<List<User>>> getAll(){
        List<User> results = this.service.getAll().getData();
        results = castMany(results);
        return new ResponseEntity<>(new CustomResponse<>(results,false,200,"Ok"),HttpStatus.OK);
    }
    @GetMapping(value = "/{id}",produces = "application/json")
    public @ResponseBody ResponseEntity<CustomResponse<Optional<User>>> getById(@PathVariable Long id){
        Optional<User> results = this.service.getById(id).getData();
        if (results != null)
            results = castOne(results);
        return new ResponseEntity<>(new CustomResponse<>(results,false,200,"Ok"),HttpStatus.OK);
    }
    @GetMapping(value = "/role",produces = "application/json")
    public @ResponseBody ResponseEntity<CustomResponse<List<User>>> getUsersByRole(@RequestParam(name = "value") String value){
        List<User> results = this.service.getByRole(value).getData();
        results = castMany(results);
        return new ResponseEntity<>(new CustomResponse<>(results,false,200,"Ok"),HttpStatus.OK);
    }
    @GetMapping(value = "/teacher", produces = "application/json")
    public @ResponseBody ResponseEntity<CustomResponse<List<User>>> getUsersByTeacher(@RequestParam(name = "value") Long value){
        List<User> results = this.service.getByTeacher(value).getData();
        results = castMany(results);
        return new ResponseEntity<>(new CustomResponse<>(results,false,200,"Ok"),HttpStatus.OK);
    }
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public @ResponseBody ResponseEntity<CustomResponse<String>> changeStatus(@PathVariable Long id){
        return new ResponseEntity<>(this.service.changeStatus(id,!this.service.getById(id).getData().get().getStatus()),HttpStatus.CREATED);
    }

    @PostMapping(value = "/",produces = "application/json")
    public @ResponseBody ResponseEntity<CustomResponse<User>> insert(@Valid @RequestBody UserDto user){
        return new  ResponseEntity<>(this.service.insert(user.castToUser()), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}",produces = "application/json")
    public @ResponseBody ResponseEntity<CustomResponse<Optional<User>>> update(@Valid @RequestBody UserDto user, @PathVariable Long id){
        user.setId(id);
        Optional<User> result = Optional.ofNullable(this.service.update(user.castToUserToUpdate()).getData());
        if (result != null)
            result = castOne(result);
        return new ResponseEntity<>(new CustomResponse<>(result,false,200,"Ok"),HttpStatus.OK);
    }

    public Optional<User> castOne(Optional<User> result){
        if (result.get().getReports() != null) {
            result.get().setReports(
                    result.get().getReports().stream().map(report ->
                            new ReportDto(
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
                            ).castToReportToUser()).collect(Collectors.toList()));
        }
        if (result.get().getPeriods() != null) {
            result.get().setPeriods(result.get().getPeriods().stream().map(
                    period -> new PeriodDto(
                            period.getId(),
                            period.getSemester(),
                            period.getStart_semester().toString().split(" ")[0].split("-")[2] + "-" +
                                    period.getStart_semester().toString().split(" ")[0].split("-")[1] + "-" +
                                    period.getStart_semester().toString().split(" ")[0].split("-")[0],

                            period.getFinish_semester().toString().split(" ")[0].split("-")[2] + "-" +
                                    period.getFinish_semester().toString().split(" ")[0].split("-")[1] + "-" +
                                    period.getFinish_semester().toString().split(" ")[0].split("-")[0],
                            period.getUser(),
                            period.getClassrooms()
                    ).castToPeriodToUser()
            ).collect(Collectors.toList()));
        }
        if (result.get().getClassroom() != null){
            result.get().setClassroom(new ClassroomDto(
                    result.get().getClassroom().getId(),
                    result.get().getClassroom().getName(),
                    result.get().getClassroom().getTotal_students(),
                    result.get().getClassroom().getCareer(),
                    result.get().getClassroom().getGrade(),
                    result.get().getClassroom().getUsers(),
                    result.get().getClassroom().getPeriod()
            ).castToClassroomToUser());
        }
        return result;
    }
    public List<User> castMany(List<User> results){
        return results.stream()
                .map(user -> new User(
                        user.getId(),
                        user.getRole(),
                        user.getName(),
                        user.getSurname(),
                        user.getEmail(),
                        user.getPassword(),
                        user.getStatus(),
                        user.getClassroom() != null ? new ClassroomDto(
                                user.getClassroom().getId(),
                                user.getClassroom().getName(),
                                user.getClassroom().getTotal_students(),
                                user.getClassroom().getCareer(),
                                user.getClassroom().getGrade(),
                                user.getClassroom().getUsers(),
                                user.getClassroom().getPeriod()
                        ).castToClassroomToUser():  null,
                        user.getCode(),
                        user.getReports().stream().map(
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
                                ).castToReportToUser()).collect(Collectors.toList()),
                        user.getPeriods().stream().map(
                                period -> new PeriodDto(
                                        period.getId(),
                                        period.getSemester(),
                                        period.getStart_semester().toString().split(" ")[0].split("-")[2]+ "-"+
                                                period.getStart_semester().toString().split(" ")[0].split("-")[1]+ "-"+
                                                period.getStart_semester().toString().split(" ")[0].split("-")[0],

                                        period.getFinish_semester().toString().split(" ")[0].split("-")[2]+"-"+
                                                period.getFinish_semester().toString().split(" ")[0].split("-")[1]+"-"+
                                                period.getFinish_semester().toString().split(" ")[0].split("-")[0],
                                        period.getUser(),
                                        period.getClassrooms()
                                ).castToPeriodToUser()
                        ).collect(Collectors.toList())
                )).collect(Collectors.toList());
    }
}
