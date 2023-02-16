package mx.edu.utez.SIBLAB.service.status;

import mx.edu.utez.SIBLAB.models.role.Role;
import mx.edu.utez.SIBLAB.models.status.Status;
import mx.edu.utez.SIBLAB.models.status.StatusRepository;
import mx.edu.utez.SIBLAB.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StatusService {
    @Autowired
    private StatusRepository repository;

    @Transactional(readOnly = true)
    public CustomResponse<List<Status>> getAll(){
        return new CustomResponse<>(this.repository.findAll(),false,200,"Ok");
    }
    @Transactional(readOnly = true)
    public CustomResponse<Optional<Status>> getById(Long id){
        if (this.repository.existsById(id)){
            return new CustomResponse<>(this.repository.findById(id),false,200,"Ok");
        }
        return new CustomResponse<>(null,true,400,"Estatus no encontrado");
    }
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Status> insert(Status status){
        return new CustomResponse<>(this.repository.saveAndFlush(status),false,200,"Ok");
    }
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Status> updateStatus(Status status){
        if (this.repository.existsById(status.getId())){
            return new CustomResponse<>(this.repository.saveAndFlush(status),false,200,"Ok");
        }
        return new CustomResponse<>(null,true,400,"Estatus no encontrado");
    }
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Boolean> delete(Long id){
        if (this.repository.existsById(id)){
            try {
                this.repository.deleteById(id);
                return new CustomResponse<>(true,false,200,"Ok");
            }catch (Exception e){
                return new CustomResponse<>(null,true,500,"Error al eliminar el estatus");
            }
        }
        return new CustomResponse<>(null,true,400,"Rol no encontrado");
    }
}
