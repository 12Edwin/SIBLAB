package mx.edu.utez.SIBLAB.service.laboratory;

import mx.edu.utez.SIBLAB.models.laboratory.Laboratory;
import mx.edu.utez.SIBLAB.models.laboratory.LaboratoryRepository;
import mx.edu.utez.SIBLAB.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LaboratoryService {

    @Autowired
    private LaboratoryRepository repository;

    @Transactional(readOnly = true)
    public CustomResponse<List<Laboratory>> getAll(){
        return new CustomResponse<>(this.repository.findAll(),false,200,"Ok");
    }

    @Transactional(readOnly = true)
    public CustomResponse<Optional<Laboratory>> getById(Long id){
        if (this.repository.existsById(id)){
            return new CustomResponse<>(this.repository.findById(id),false,200,"Ok");
        }
        return new CustomResponse<>(null,true,400,"Laboratorio no encontrado");
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Laboratory> insert(Laboratory laboratory){
        return new CustomResponse<>(this.repository.save(laboratory),false,200,"Ok");
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Laboratory> update(Laboratory laboratory){
        if (this.repository.existsById(laboratory.getId())){
            return new CustomResponse<>(this.repository.saveAndFlush(laboratory),false,200,"Ok");
        }
        return new CustomResponse<>(null,true,400,"Laboratorio no encontrado");
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Boolean> delete(Long id){
        if (this.repository.existsById(id)){
            Boolean status = !this.repository.findById(id).get().getStatus();
            return new CustomResponse<>(this.repository.changeStatusById(id,status) == 1,false,200,"Ok");
        }
        return new CustomResponse<>(null,true,400,"Laboratorio no encontrado");
    }
}
