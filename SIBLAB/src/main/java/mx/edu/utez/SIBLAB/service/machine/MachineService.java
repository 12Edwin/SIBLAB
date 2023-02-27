package mx.edu.utez.SIBLAB.service.machine;

import mx.edu.utez.SIBLAB.models.machine.Machine;
import mx.edu.utez.SIBLAB.models.machine.MachineRepository;
import mx.edu.utez.SIBLAB.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MachineService {
    @Autowired
    private MachineRepository repository;

    @Transactional(readOnly = true)
    public CustomResponse<List<Machine>> getAll(){
        return new CustomResponse<>(this.repository.findAll(),false,200,"Ok");
    }
    @Transactional(readOnly = true)
    public CustomResponse<Optional<Machine>> getById(Long id){
        return new CustomResponse<>(this.repository.findById(id),false,200,"Ok");
    }
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Machine> insert(Machine machine){
        return new CustomResponse<>(this.repository.saveAndFlush(machine),false,200,"Created");
    }
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Machine> update(Machine machine){
        return new CustomResponse<>(this.repository.saveAndFlush(machine),false,200,"Updaated");
    }
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Boolean> changeStatus(Long id, Boolean status){
        return new CustomResponse<>(this.repository.changeStatusById(id,status) == 1,false,200,"Changed");
    }
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Boolean> patch(Machine machine){
        return new CustomResponse<>(this.repository.updateImageById(machine.getId(), machine.getPath_image()) == 1,false,200,"Ok");
    }
}
