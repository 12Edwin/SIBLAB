package mx.edu.utez.SIBLAB.controller.building;

import mx.edu.utez.SIBLAB.controller.building.dto.BuildingDto;
import mx.edu.utez.SIBLAB.controller.laboratory.dto.LaboratoryDto;
import mx.edu.utez.SIBLAB.models.building.Building;
import mx.edu.utez.SIBLAB.service.building.BuildingService;
import mx.edu.utez.SIBLAB.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/api-siblab/building")
public class BuildingController {
    @Autowired
    private BuildingService service;

    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<CustomResponse<List<Building>>> getAll(){
        List<Building> buildings = this.service.getAll().getData();
        buildings = castToMany(buildings);
        return new ResponseEntity<>(new CustomResponse<>(buildings,false,200,"Ok"),HttpStatus.OK);
    }
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<CustomResponse<Optional<Building>>> getById(@PathVariable Long id){
        Optional<Building> building = this.service.getById(id).getData();
        building = castToOne(building);
        return new ResponseEntity<>(new CustomResponse<>(building,false,200,"Ok"),HttpStatus.OK);    }
    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<CustomResponse<Building>> insert(@RequestBody BuildingDto building){
        return new ResponseEntity<>(this.service.insert(building.castToBuilding()),HttpStatus.CREATED);
    }
    @PutMapping(value = "/{id}",produces = "application/json")
    public ResponseEntity<CustomResponse<Building>> update(@RequestBody BuildingDto building, @PathVariable Long id){
        building.setId(id);
        return new ResponseEntity<>(this.service.update(building.castToBuildingToUpdate()),HttpStatus.CREATED);
    }
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<CustomResponse<Boolean>> delete(@PathVariable Long id){
        return new ResponseEntity<>(this.service.delete(id),HttpStatus.OK);
    }

    public Optional<Building> castToOne(Optional<Building> building){
        building.get().setLaboratories(
                building.get().getLaboratories().stream().map(
                        laboratory -> new LaboratoryDto(
                                laboratory.getId(),
                                laboratory.getName(),
                                laboratory.getDescription(),
                                laboratory.getStatus(),
                                laboratory.getMachines(),
                                laboratory.getBuilding()
                        ).castToLaboratoryToBuild()
                ).collect(Collectors.toList())
        );
        return building;
    }
    public List<Building> castToMany(List<Building> buildings){
        buildings = buildings.stream().map(
                building -> new Building(
                        building.getId(),
                        building.getName(),
                        building.getLocation(),
                        building.getLaboratories().stream().map(
                                laboratory -> new LaboratoryDto(
                                        laboratory.getId(),
                                        laboratory.getName(),
                                        laboratory.getDescription(),
                                        laboratory.getStatus(),
                                        laboratory.getMachines(),
                                        laboratory.getBuilding()
                                ).castToLaboratoryToBuild()
                        ).collect(Collectors.toList())
                )
        ).collect(Collectors.toList());
        return buildings;
    }

}
