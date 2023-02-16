package mx.edu.utez.SIBLAB.service.role;

import lombok.Setter;
import mx.edu.utez.SIBLAB.models.role.Role;
import mx.edu.utez.SIBLAB.models.role.RoleRepository;
import mx.edu.utez.SIBLAB.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoleService {
    @Autowired
    private RoleRepository repository;

    @Transactional(readOnly = true)
    public CustomResponse<List<Role>> getAll(){
        return new CustomResponse<>(this.repository.findAll(),false,200,"Ok");
    }
    @Transactional(readOnly = true)
    public CustomResponse<Optional<Role>> getById(Long id){
        if (this.repository.existsById(id)) {
            return new CustomResponse<>(this.repository.findById(id), false, 200, "Ok");
        }
        return new CustomResponse<>(null,true,400,"Rol no enontrado");
    }
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Role> insert(Role role){
        return new CustomResponse<>(this.repository.saveAndFlush(role),false,200,"Ok");
    }
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Role> update(Role role){
        if (this.repository.existsById(role.getId())) {
            return new CustomResponse<>(this.repository.saveAndFlush(role), false, 200, "Ok");
        }
        return new CustomResponse<>(null,true,400,"Rol no encotrado");
    }
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Boolean> delete(Long id){
        if (this.repository.existsById(id)){
            try {
                this.repository.deleteById(id);
                return new CustomResponse<>(true,false,200,"Ok");
            }catch (Exception e){
                return new CustomResponse<>(null,true,500,"Ocurrió un error al eliminar");
            }
        }
        return new CustomResponse<>(null,true,400,"No se encontró el rol");
    }
}
