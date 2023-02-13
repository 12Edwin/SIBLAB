package mx.edu.utez.SIBLAB.controller.user;

import mx.edu.utez.SIBLAB.controller.user.dtos.UserDto;
import mx.edu.utez.SIBLAB.controller.user.dtos.UserDtoUpdate;
import mx.edu.utez.SIBLAB.models.user.User;
import mx.edu.utez.SIBLAB.service.user.UserService;
import mx.edu.utez.SIBLAB.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.tags.Param;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api-siblab/user")
@CrossOrigin(origins = {"*"})
public class UserController {
    @Autowired
    private UserService service;
    @GetMapping(value = "/")
    public @ResponseBody ResponseEntity<CustomResponse<List<User>>> getAll(){

        return new  ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
    }
    @GetMapping(value = "/{id}",produces = "application/json")
    public @ResponseBody ResponseEntity<CustomResponse<Optional<User>>> getById(@PathVariable Long id){
        return new ResponseEntity<>(this.service.getById(id),HttpStatus.OK);
    }
    @GetMapping(value = "/role",produces = "application/json")
    public @ResponseBody ResponseEntity<CustomResponse<List<User>>> getUsersByRole(@RequestParam(name = "value") String value){
        return new ResponseEntity<>(this.service.getByRole(value),HttpStatus.OK);
    }
    @GetMapping(value = "/teacher", produces = "application/json")
    public @ResponseBody ResponseEntity<CustomResponse<List<User>>> getUsersByTeacher(@RequestParam(name = "value") Long value){
        return new ResponseEntity<>(this.service.getByTeacher(value),HttpStatus.OK);
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
    public @ResponseBody ResponseEntity<CustomResponse<User>> update(@Valid @RequestBody UserDtoUpdate user, @PathVariable Long id){
        user.setId(id);
        return new ResponseEntity<>(this.service.update(user.castToUserForUpdate()),HttpStatus.OK);
    }
}
