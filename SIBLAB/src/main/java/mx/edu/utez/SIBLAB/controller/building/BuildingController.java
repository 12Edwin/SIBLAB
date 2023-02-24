package mx.edu.utez.SIBLAB.controller.building;

import mx.edu.utez.SIBLAB.models.building.Building;
import mx.edu.utez.SIBLAB.service.building.BuildingService;
import mx.edu.utez.SIBLAB.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/api-siblab/building")
public class BuildingController {
    @Autowired
    private BuildingService service;

    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<CustomResponse<List<Building>>> getAll(){
        return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
    }
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<CustomResponse<Optional<Building>>> getById(@PathVariable Long id){
        return new ResponseEntity<>(this.service.getById(id),HttpStatus.OK);
    }
    @PostMapping(value = "/", produces = "aplication/json")
    public ResponseEntity<CustomResponse<Building>> insert(@RequestBody Building building){
        return new ResponseEntity<>(this.service.insert(building),HttpStatus.CREATED);
    }
    @PutMapping(value = "/{id}",produces = "application/json")
    public ResponseEntity<CustomResponse<Building>> update(@RequestBody Building building, @PathVariable Long id){
        building.setId(id);
        return new ResponseEntity<>(this.service.update(building),HttpStatus.CREATED);
    }
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<CustomResponse<Boolean>> delete(@PathVariable Long id){
        return new ResponseEntity<>(this.service.delete(id),HttpStatus.OK);
    }
}
