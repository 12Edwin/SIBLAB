package mx.edu.utez.SIBLAB.controller.classroom;

import mx.edu.utez.SIBLAB.controller.classroom.dtos.ClassroomDto;
import mx.edu.utez.SIBLAB.controller.period.dto.PeriodDto;
import mx.edu.utez.SIBLAB.controller.user.dtos.UserDto;
import mx.edu.utez.SIBLAB.models.classroom.Classroom;
import mx.edu.utez.SIBLAB.service.classroom.ClassroomService;
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
@RequestMapping("/api-siblab/classroom")
@CrossOrigin(origins = {"*"})
public class ClassroomController {
    @Autowired
    private ClassroomService service;

    @GetMapping(value = "/",produces = "application/json")
    public @ResponseBody ResponseEntity<CustomResponse<List<Classroom>>> getAll(){
        List<Classroom> classrooms = this.service.getAll().getData();
        classrooms = castToMany(classrooms);
        return new ResponseEntity<>(new CustomResponse<>(classrooms,false,200,"Ok"),HttpStatus.OK);
    }
    @GetMapping(value = "/{id}", produces = "application/json")
    public @ResponseBody ResponseEntity<CustomResponse<Optional<Classroom>>> getById(@PathVariable Long id){
        Optional<Classroom> classroom = this.service.getById(id).getData();
        if (classroom != null)
            classroom = castToOne(classroom);
        return new ResponseEntity<>(new CustomResponse<>(classroom,false,200,"Ok"),HttpStatus.OK);    }
    @PostMapping(value = "/", produces = "application/json")
    public @ResponseBody ResponseEntity<CustomResponse<Classroom>> insert(@RequestBody @Valid ClassroomDto classroom){
        return new ResponseEntity<>(this.service.insert(classroom.castToClassroom()),HttpStatus.CREATED);
    }
    @PutMapping(value = "/{id}",produces = "application/json")
    public @ResponseBody ResponseEntity<CustomResponse<Optional<Classroom>>> update(@PathVariable Long id, @RequestBody @Valid ClassroomDto classroom){
        classroom.setId(id);
        Optional<Classroom> result = Optional.ofNullable(this.service.update(classroom.castToClassroomToUpdate()).getData());
        if (result != null)
            result = castToOne(result);
        return new ResponseEntity<>(new CustomResponse<>(result,false,200,"Ok"),HttpStatus.CREATED );
    }
    @DeleteMapping(value = "/{id}",produces = "application/json")
    public @ResponseBody ResponseEntity<CustomResponse<Boolean>> changeStatus(@PathVariable Long id){
        return new ResponseEntity<>(this.service.delete(id),HttpStatus.OK);
    }

    public Optional<Classroom> castToOne(Optional<Classroom> classroom){
        if (classroom.get().getUsers() != null) {
            classroom.get().setUsers(classroom.get().getUsers().stream().map(
                    user -> new UserDto(
                            user.getId(),
                            user.getName(),
                            user.getSurname(),
                            user.getEmail(),
                            user.getPassword(),
                            user.getRole(),
                            user.getClassroom(),
                            user.getCode(),
                            user.getStatus(),
                            user.getPeriods(),
                            user.getReports()
                    ).castToUserToClass()
            ).collect(Collectors.toList()));
        }
        if (classroom.get().getPeriod() != null) {
            classroom.get().setPeriod(new PeriodDto(
                    classroom.get().getPeriod().getId(),
                    classroom.get().getPeriod().getSemester(),
                    classroom.get().getPeriod().getUser(),
                    classroom.get().getPeriod().getClassrooms()
            ).castToPeriodToClass());
        }
        return classroom;
    }
    public List<Classroom> castToMany(List<Classroom>classrooms){
        return classrooms.stream().map(
                classroom -> new Classroom(
                        classroom.getId(),
                        classroom.getName(),
                        classroom.getCareer(),
                        classroom.getGrade(),
                        classroom.getTotal_students(),
                        classroom.getUsers().stream().map(
                                user -> new UserDto(
                                        user.getId(),
                                        user.getName(),
                                        user.getSurname(),
                                        user.getEmail(),
                                        user.getPassword(),
                                        user.getRole(),
                                        user.getClassroom(),
                                        user.getCode(),
                                        user.getStatus(),
                                        user.getPeriods(),
                                        user.getReports()
                                ).castToUserToClass()
                        ).collect(Collectors.toList()),
                        new PeriodDto(
                                classroom.getPeriod().getId(),
                                classroom.getPeriod().getSemester(),
                                classroom.getPeriod().getUser(),
                                classroom.getPeriod().getClassrooms()
                        ).castToPeriodToClass()
                )
        ).collect(Collectors.toList());
    }
}
