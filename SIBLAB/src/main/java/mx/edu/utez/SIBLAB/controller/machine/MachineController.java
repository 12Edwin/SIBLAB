package mx.edu.utez.SIBLAB.controller.machine;

import mx.edu.utez.SIBLAB.controller.laboratory.dto.LaboratoryDto;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api-siblab/machine")
@CrossOrigin(origins = {"*"})
public class MachineController {
    @Autowired
    private MachineService service;
    @GetMapping(value = "/",produces = "application/json")
    public @ResponseBody ResponseEntity<CustomResponse<List<Machine>>> getAll(){
        List<Machine> results = this.service.getAll().getData();
        results = castMany(results);
        return new ResponseEntity<>(new CustomResponse<>(results,false,200,"Ok"), HttpStatus.OK);
    }
    @GetMapping(value = "/{id}",produces = "application/json")
    public @ResponseBody ResponseEntity<CustomResponse<Optional<Machine>>> getById(@PathVariable Long id){
        Optional<Machine> results = this.service.getById(id).getData();
        if (results != null)
            results = castOne(results);
        return new ResponseEntity<>(new CustomResponse<>(results,false,200,"Ok"), HttpStatus.OK);
    }
    @PostMapping(value = "/", produces = "application/json")
    public @ResponseBody ResponseEntity<CustomResponse<Machine>> insert(@RequestBody @Valid MachineDto machine){
        return new ResponseEntity<>(this.service.insert(machine.castToMachine()),HttpStatus.CREATED);
    }
    @PutMapping(value = "/{id}",produces = "application/json")
    public @ResponseBody ResponseEntity<CustomResponse<?>> update(@PathVariable Long id,@RequestBody @Valid MachineDto machine){
        machine.setId(id);
        Optional<Machine> result = Optional.ofNullable(this.service.update(machine.castToMachineToUpdate()).getData());
        if (result != null)
            result = castOne(result);
        return new ResponseEntity<>(new CustomResponse<>(result,false,200,"Ok"),HttpStatus.CREATED);
    }
    @DeleteMapping(value = "/{id}",produces = "application/json")
    public @ResponseBody ResponseEntity<CustomResponse<Boolean>> changeStatus(@PathVariable Long id){
        boolean status = this.service.getById(id).getData().get().getStatus();
        status = !status;
        return new ResponseEntity<>(this.service.changeStatus(id,status),HttpStatus.CREATED);
    }

    public Optional<Machine> castOne(Optional<Machine> result){
        result.get().setReport(null);
        if (result.get().getLaboratory() != null) {
            result.get().setLaboratory(
                    new LaboratoryDto(
                            result.get().getLaboratory().getId(),
                            result.get().getLaboratory().getName(),
                            result.get().getLaboratory().getDescription(),
                            result.get().getLaboratory().getStatus(),
                            result.get().getLaboratory().getMachines(),
                            result.get().getLaboratory().getBuilding()
                    ).castToLaboratoryToMachine()
            );
        }
        return result;
    }
    public List<Machine> castMany(List<Machine> results){
        return results.stream()
                .map(machine -> new Machine(
                        machine.getId(),
                        machine.getName(),
                        machine.getBrand(),
                        machine.getPath_image(),
                        machine.getCpu(),
                        machine.getHard_disk(),
                        machine.getStatus(),
                        machine.getSpecific_features(),
                        null,
                        new LaboratoryDto(
                                machine.getLaboratory().getId(),
                                machine.getLaboratory().getName(),
                                machine.getLaboratory().getDescription(),
                                machine.getLaboratory().getStatus(),
                                machine.getLaboratory().getMachines(),
                                machine.getLaboratory().getBuilding()
                        ).castToLaboratoryToMachine()
                )).collect(Collectors.toList());
    }
}
