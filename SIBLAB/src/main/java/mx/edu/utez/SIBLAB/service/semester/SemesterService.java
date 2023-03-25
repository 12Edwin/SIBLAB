package mx.edu.utez.SIBLAB.service.semester;

import mx.edu.utez.SIBLAB.models.semester.Semester;
import mx.edu.utez.SIBLAB.models.semester.SemesterRepository;
import mx.edu.utez.SIBLAB.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SemesterService {
    @Autowired
    private SemesterRepository repository;

    @Transactional(readOnly = true)
    public CustomResponse<List<Semester>> getAll (){
        return new CustomResponse<>(this.repository.findAll(),false,200,"Ok");
    }

    @Transactional(readOnly = true)
    public CustomResponse<Optional<Semester>> getById(Long id){
        return new CustomResponse<>(this.repository.findById(id),false,200,"Ok");
    }

    @Transactional (rollbackFor = {SQLException.class})
    public CustomResponse<Semester> insert(Semester semester){
        return new CustomResponse<>(this.repository.save(semester),false,200,"ok");
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Semester> update(Semester semester) {
        if (this.repository.existsById(semester.getId()))
            return new CustomResponse<>(this.repository.save(semester), false, 200, "ok");
        return new CustomResponse<>(null,true,200,"Cuatrimestre no encontrado");
    }
}
