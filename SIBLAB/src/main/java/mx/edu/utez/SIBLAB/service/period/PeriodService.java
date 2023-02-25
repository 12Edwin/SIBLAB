package mx.edu.utez.SIBLAB.service.period;

import mx.edu.utez.SIBLAB.models.period.Period;
import mx.edu.utez.SIBLAB.models.period.PeriodRepository;
import mx.edu.utez.SIBLAB.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PeriodService {
    @Autowired
    private PeriodRepository repository;

    @Transactional(readOnly = true)
    public CustomResponse<List<Period>> getAll(){
        return new CustomResponse<>(this.repository.findAll(),false,200,"Ok");
    }

    @Transactional(readOnly = true)
    public CustomResponse<Optional<Period>> getById(Long id){
        return new CustomResponse<>(this.repository.findById(id),false,200,"Ok");
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Period> insert(Period period){
        return new CustomResponse<>(this.repository.save(period),false,200,"Ok");
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Period> update(Period period){
        if (this.repository.existsById(period.getId())){
            return new CustomResponse<>(this.repository.saveAndFlush(period),false,200,"Ok");
        }
        return new CustomResponse<>(null,true,400,"Periodo no encontrado");
    }

}
