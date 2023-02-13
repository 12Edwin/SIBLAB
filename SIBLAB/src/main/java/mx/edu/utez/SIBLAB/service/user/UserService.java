package mx.edu.utez.SIBLAB.service.user;

import mx.edu.utez.SIBLAB.models.user.User;
import mx.edu.utez.SIBLAB.models.user.UserRepository;
import mx.edu.utez.SIBLAB.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    @Transactional(readOnly = true)
    public CustomResponse<List<User>> getAll(){
        return new CustomResponse<>(this.repository.findAll(), false, 200, "OK");
    }
    @Transactional(readOnly = true)
    public CustomResponse<Optional<User>> getById(Long id){
        if (this.repository.existsById(id)){
            return new CustomResponse<>(this.repository.findById(id),false,200,"Ok");
        }
        return new CustomResponse<>(null,true,400,"Usuario no encontrado");
    }
    @Transactional(readOnly = true)
    public CustomResponse<List<User>> getByTeacher(Long id_teacher){
        return new CustomResponse<>(this.repository.findUsersByTeacher(id_teacher),false,200,"Ok");
    }
    @Transactional(readOnly = true)
    public CustomResponse<List<User>> getByRole(String role){
        return new CustomResponse<>(this.repository.findUsersByRol(role),false,200,"Ok");
    }
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<User> insert(User user){
        if (this.repository.existsByEmail(user.getEmail())){
            return new CustomResponse<>(null,true,400,"Usuario existente");
        }
        return new CustomResponse<>(this.repository.saveAndFlush(user), false, 200, "OK");
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<User> update(User user){
        if (!this.repository.existsById(user.getId())){
            return new CustomResponse<>(null,true,400,"Usuario no encontrado");
        }
        return new CustomResponse<>(this.repository.saveAndFlush(user),false,200,"Usuario actualizado");
    }
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<String> changeStatus(Long id,Boolean status){
        if (this.repository.existsById(id)){
            return new CustomResponse<>(this.repository.changeStatusById(id, status) == 1? "Status changed":"Error",false,200,"Updated");
        }
        return new CustomResponse<>(null,true,400,"Registro no encontrado");
    }
}
