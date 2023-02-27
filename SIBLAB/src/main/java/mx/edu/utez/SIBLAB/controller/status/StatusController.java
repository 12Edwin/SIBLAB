package mx.edu.utez.SIBLAB.controller.status;

import mx.edu.utez.SIBLAB.controller.status.dtos.StatusDto;
import mx.edu.utez.SIBLAB.models.status.Status;
import mx.edu.utez.SIBLAB.service.status.StatusService;
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
@RequestMapping("/api-siblab/status")
public class StatusController {
    @Autowired
    private StatusService service;

    @GetMapping(value = "/",produces = "application/json")
    public ResponseEntity<CustomResponse<List<Status>>> getAll(){
        return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
    }
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<CustomResponse<Optional<Status>>> getById(@PathVariable Long id){
        return new ResponseEntity<>(this.service.getById(id),HttpStatus.OK);
    }
    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<CustomResponse<Status>> insert(@RequestBody @Valid StatusDto status){
        return new ResponseEntity<>(this.service.insert(status.castToStatus()),HttpStatus.CREATED);
    }
    @PutMapping(value = "/{id}",produces = "application/json")
    public ResponseEntity<CustomResponse<Status>> update(@RequestBody @Valid StatusDto status, @PathVariable Long id){
        status.setId(id);
        return new ResponseEntity<>(this.service.updateStatus(status.castToStatusToUpdate()),HttpStatus.CREATED);
    }
    @DeleteMapping(value = "/{id}",produces = "application/json")
    public ResponseEntity<CustomResponse<Boolean>> delete(@PathVariable Long id){
        return new ResponseEntity<>(this.service.delete(id),HttpStatus.OK);
    }
}
