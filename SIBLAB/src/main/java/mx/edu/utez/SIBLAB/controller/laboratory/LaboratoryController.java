package mx.edu.utez.SIBLAB.controller.laboratory;

import mx.edu.utez.SIBLAB.controller.building.dto.BuildingDto;
import mx.edu.utez.SIBLAB.controller.laboratory.dto.LaboratoryDto;
import mx.edu.utez.SIBLAB.controller.machine.dtos.MachineDto;
import mx.edu.utez.SIBLAB.models.laboratory.Laboratory;
import mx.edu.utez.SIBLAB.service.laboratory.LaboratoryService;
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
@RequestMapping("/api-siblab/laboratory")
public class LaboratoryController {

    @Autowired
    private LaboratoryService service;

    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<CustomResponse<List<Laboratory>>> getAll(){
        List<Laboratory> laboratories = this.service.getAll().getData();
        laboratories = castToMany(laboratories);
        return new ResponseEntity<>(new CustomResponse<>(laboratories,false,200,"Ok"),HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<CustomResponse<Optional<Laboratory>>> getById (@PathVariable Long id){
        Optional<Laboratory> laboratory = this.service.getById(id).getData();
        if (laboratory != null)
            laboratory = castToOne(laboratory);
        return new ResponseEntity<>(new CustomResponse<>(laboratory,false,200,"Ok"),HttpStatus.OK);    }

    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<CustomResponse<Laboratory>> insert(@RequestBody @Valid LaboratoryDto laboratory){
        return new ResponseEntity<>(this.service.insert(laboratory.castToLaboratory()), HttpStatus.CREATED );
    }

    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<CustomResponse<Optional<Laboratory>>> update(@PathVariable Long id, @RequestBody LaboratoryDto laboratory){
        laboratory.setId(id);
        Optional<Laboratory> result = Optional.ofNullable(this.service.update(laboratory.castToLaboratoryToUpdate()).getData());
        if (result != null)
            result = castToOne(result);
        return new ResponseEntity<>(new CustomResponse<>(result,false,200,"Ok"),HttpStatus.CREATED );
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<CustomResponse<Boolean>> delete(@PathVariable Long id){
        return new ResponseEntity<>(this.service.delete(id),HttpStatus.OK );
    }


    public Optional<Laboratory> castToOne(Optional<Laboratory> laboratory){
        if (laboratory.get().getMachines() != null) {
            laboratory.get().setMachines(laboratory.get().getMachines().stream().map(
                    machine -> new MachineDto(
                            machine.getId(),
                            machine.getName(),
                            machine.getBrand(),
                            machine.getPath_image(),
                            machine.getCpu(),
                            machine.getHard_disk(),
                            machine.getStatus(),
                            machine.getSpecific_features(),
                            machine.getLaboratory(),
                            machine.getReport()
                    ).castToMachineToLab()
            ).collect(Collectors.toList()));
        }
        if (laboratory.get().getBuilding() != null) {
            laboratory.get().setBuilding(
                    new BuildingDto(
                            laboratory.get().getBuilding().getId(),
                            laboratory.get().getBuilding().getName(),
                            laboratory.get().getBuilding().getLocation(),
                            laboratory.get().getBuilding().getLaboratories()
                    ).castToBuildingToLab()
            );
        }
        return laboratory;
    }
    public List<Laboratory> castToMany(List<Laboratory> laboratories){
        laboratories = laboratories.stream().map(
                laboratory ->
                        new Laboratory(
                        laboratory.getId(),
                        laboratory.getName(),
                        laboratory.getDescription(),
                        laboratory.getStatus(),
                        laboratory.getMachines().stream().map(
                                machine -> new MachineDto(
                                        machine.getId(),
                                        machine.getName(),
                                        machine.getBrand(),
                                        machine.getPath_image(),
                                        machine.getHard_disk(),
                                        machine.getCpu(),
                                        machine.getStatus(),
                                        machine.getSpecific_features(),
                                        machine.getLaboratory(),
                                        machine.getReport()
                                ).castToMachineToLab()
                        ).collect(Collectors.toList()),
                        new BuildingDto(
                                laboratory.getBuilding().getId(),
                                laboratory.getBuilding().getName(),
                                laboratory.getBuilding().getLocation(),
                                laboratory.getBuilding().getLaboratories()
                        ).castToBuildingToLab()
                )
        ).collect(Collectors.toList());
        return laboratories;
    }
}
