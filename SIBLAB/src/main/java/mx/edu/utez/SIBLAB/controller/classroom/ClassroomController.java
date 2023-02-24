package mx.edu.utez.SIBLAB.controller.classroom;

import mx.edu.utez.SIBLAB.controller.classroom.dtos.ClassroomDto;
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

@RestController
@RequestMapping("/api-siblab/classroom")
@CrossOrigin(origins = {"*"})
public class ClassroomController {
    @Autowired
    private ClassroomService service;

    @GetMapping(value = "/",produces = "application/json")
    public @ResponseBody ResponseEntity<CustomResponse<List<Classroom>>> getAll(){
        return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
    }
    @GetMapping(value = "/{id}", produces = "application/json")
    public @ResponseBody ResponseEntity<CustomResponse<Optional<Classroom>>> getById(@PathVariable Long id){
        return new ResponseEntity<>(this.service.getById(id),HttpStatus.OK);
    }
    @PostMapping(value = "/", produces = "application/json")
    public @ResponseBody ResponseEntity<CustomResponse<Classroom>> insert(@RequestBody @Valid ClassroomDto classroom){
        return new ResponseEntity<>(this.service.insert(classroom.castToClassroom()),HttpStatus.CREATED);
    }
    @PutMapping(value = "/{id}",produces = "application/json")
    public @ResponseBody ResponseEntity<CustomResponse<Classroom>> update(@PathVariable Long id, @RequestBody @Valid ClassroomDto classroom){
        classroom.setId(id);
        return new ResponseEntity<>(this.service.update(classroom.castToClassroom()),HttpStatus.CREATED );
    }
    @DeleteMapping(value = "/{id}",produces = "application/json")
    public @ResponseBody ResponseEntity<CustomResponse<Boolean>> changeStatus(@PathVariable Long id){
        return new ResponseEntity<>(this.service.delete(id),HttpStatus.OK);
    }
}
