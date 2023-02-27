package mx.edu.utez.SIBLAB.controller.role;

import mx.edu.utez.SIBLAB.controller.role.dtos.RoleDto;
import mx.edu.utez.SIBLAB.models.role.Role;
import mx.edu.utez.SIBLAB.service.role.RoleService;
import mx.edu.utez.SIBLAB.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/api-siblab/role")
public class RoleController {
    @Autowired
    private RoleService service;

    @GetMapping(value ="/", produces = "application/json")
    public @ResponseBody ResponseEntity<CustomResponse<List<Role>>> getAll(){
        return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
    }
    @GetMapping(value = "/{id}",produces = "application/json")
    public @ResponseBody ResponseEntity<CustomResponse<Optional<Role>>> getById(@PathVariable Long id){
        return new ResponseEntity<>(this.service.getById(id),HttpStatus.OK);
    }
    @PostMapping(value = "/",produces = "application/json")
    public @ResponseBody ResponseEntity<CustomResponse<Role>> insert(@RequestBody @Valid RoleDto role){
        return new ResponseEntity<>(this.service.insert(role.castToRole()),HttpStatus.CREATED);
    }
    @PutMapping(value = "/{id}",produces = "application/json")
    public @ResponseBody ResponseEntity<CustomResponse<Role>> update(@PathVariable Long id, @RequestBody @Valid RoleDto role){
        role.setId(id);
        return new ResponseEntity<>(this.service.update(role.castToRoleToUpdate()),HttpStatus.CREATED);
    }
    @DeleteMapping(value = "/{id}",produces = "application/json")
    public @ResponseBody ResponseEntity<CustomResponse<Boolean>> delete(@PathVariable Long id){
        return new ResponseEntity<>(this.service.delete(id),HttpStatus.OK);
    }
}
