package mx.edu.utez.SIBLAB.controller.laboratory;

import mx.edu.utez.SIBLAB.models.laboratory.Laboratory;
import mx.edu.utez.SIBLAB.service.laboratory.LaboratoryService;
import mx.edu.utez.SIBLAB.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/api-siblab/laboratory")
public class LaboratoryController {

    @Autowired
    private LaboratoryService service;

    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<CustomResponse<List<Laboratory>>> getAll(){
        return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<CustomResponse<Optional<Laboratory>>> getById (@PathVariable Long id){
        return new ResponseEntity<>(this.service.getById(id),HttpStatus.OK);
    }

    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<CustomResponse<Laboratory>> insert(@RequestBody Laboratory laboratory){
        return new ResponseEntity<>(this.service.insert(laboratory), HttpStatus.CREATED );
    }

    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<CustomResponse<Laboratory>> update(@PathVariable Long id, @RequestBody Laboratory laboratory){
        laboratory.setId(id);
        return new ResponseEntity<>(this.service.update(laboratory),HttpStatus.CREATED );
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<CustomResponse<Boolean>> delete(@PathVariable Long id){
        return new ResponseEntity<>(this.service.delete(id),HttpStatus.OK );
    }
}
