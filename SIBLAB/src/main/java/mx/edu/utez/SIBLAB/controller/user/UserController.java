package mx.edu.utez.SIBLAB.controller.user;

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
    public @ResponseBody ResponseEntity<CustomResponse<String>> changeStatus(@PathVariable Long id, @RequestParam(name = "status") Boolean status){
        return new ResponseEntity<>(this.service.changeStatus(id,status),HttpStatus.CREATED);
    }

    @PostMapping(value = "/",produces = "application/json")
    public @ResponseBody ResponseEntity<CustomResponse<User>> insert(@Valid @RequestBody UserDto user){
        return new  ResponseEntity<>(this.service.insert(user.castToUser()), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}",produces = "application/json")
    public @ResponseBody ResponseEntity<CustomResponse<User>> update(@Valid @RequestBody UserDto user, @PathVariable Long id){
        user.setId(id);
        return new ResponseEntity<>(this.service.update(user.castToUser()),HttpStatus.OK);
    }

    public Optional<User> castOne(Optional<User> result){
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
                        user.getCareer(),
                        user.getDivision(),
                        user.getClassroom(),
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
                                ).castToReportToUser()).collect(Collectors.toList())
                )).collect(Collectors.toList());
    }
}
