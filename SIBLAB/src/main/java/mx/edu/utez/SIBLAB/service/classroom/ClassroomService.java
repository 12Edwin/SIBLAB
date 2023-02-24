package mx.edu.utez.SIBLAB.service.classroom;

import mx.edu.utez.SIBLAB.models.classroom.Classroom;
import mx.edu.utez.SIBLAB.models.classroom.GroupRepository;
import mx.edu.utez.SIBLAB.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.stylesheets.LinkStyle;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClassroomService {
    @Autowired
    private GroupRepository repository;

    @Transactional(readOnly = true)
    public CustomResponse<List<Classroom>> getAll() {
        return new CustomResponse<>(this.repository.findAll(), false, 200, "Ok");
    }

    @Transactional(readOnly = true)
    public CustomResponse<Optional<Classroom>> getById(Long id){
        if (this.repository.existsById(id)){
            return new CustomResponse<>(this.repository.findById(id),false,200,"Ok");
        }
        return new CustomResponse<>(null,true,400,"Ok");
    }
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Classroom> insert(Classroom classroom){
        return new CustomResponse<>(this.repository.save(classroom),false,200,"Ok");
    }
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Classroom> update(Classroom classroom){
        if (this.repository.existsById(classroom.getId())){
            return new CustomResponse<>(this.repository.saveAndFlush(classroom),false,200,"Ok");
        }
        return new CustomResponse<>(null,true,400,"Grupo no encontrado");
    }
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Boolean> delete(Long id){
        if (this.repository.existsById(id)){
            try{
                this.repository.deleteById(id);
                return new CustomResponse<>(true,false,200,"Ok");
            }catch (Exception e){
                return new CustomResponse<>(null,true,500,"Ocurrió un errror al eliminar");
            }
        }
        return new CustomResponse<>(null,true,400,"Grupo no encontrado");
    }
}
