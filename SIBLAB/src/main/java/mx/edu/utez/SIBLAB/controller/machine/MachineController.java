package mx.edu.utez.SIBLAB.controller.machine;

import lombok.AllArgsConstructor;
import mx.edu.utez.SIBLAB.controller.machine.dtos.MachineDto;
import mx.edu.utez.SIBLAB.models.machine.Machine;
import mx.edu.utez.SIBLAB.service.machine.MachineService;
import mx.edu.utez.SIBLAB.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api-siblab/machine")
@CrossOrigin(origins = {"*"})
public class MachineController {
    @Autowired
    private MachineService service;

    @GetMapping(value = "/",produces = "application/json")
    public @ResponseBody ResponseEntity<CustomResponse<List<Machine>>> getAll(){
        return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
    }
    @GetMapping(value = "/{id}",produces = "application/json")
    public @ResponseBody ResponseEntity<CustomResponse<Optional<Machine>>> getById(@PathVariable Long id){
        return new ResponseEntity<>(this.service.getById(id),HttpStatus.OK);
    }
    @PostMapping(value = "/",produces = "application/json")
    public @ResponseBody ResponseEntity<CustomResponse<Machine>> insert(@RequestBody @Valid MachineDto machine){
        return new ResponseEntity<>(this.service.insert(machine.castToMachine()),HttpStatus.CREATED);
    }
    @PutMapping(value = "/{id}",produces = "application/json")
    public @ResponseBody ResponseEntity<CustomResponse<Machine>> update(@PathVariable Long id,@RequestBody @Valid MachineDto machine){
        return new ResponseEntity<>(this.service.update(machine.castToMachineUpdate(),id),HttpStatus.CREATED);
    }
    @DeleteMapping(value = "/{id}",produces = "application/json")
    public @ResponseBody ResponseEntity<CustomResponse<Boolean>> changeStatus(@PathVariable Long id, @RequestBody @Valid MachineDto machine){
        return new ResponseEntity<>(this.service.changeStatus(id,machine.castToMachineDelete().getStatus()),HttpStatus.CREATED);
    }
}
