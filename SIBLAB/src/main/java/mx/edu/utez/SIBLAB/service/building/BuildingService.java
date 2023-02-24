package mx.edu.utez.SIBLAB.service.building;

import mx.edu.utez.SIBLAB.models.building.Building;
import mx.edu.utez.SIBLAB.models.building.BuildingRepository;
import mx.edu.utez.SIBLAB.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BuildingService {
    @Autowired
    private BuildingRepository repository;

    @Transactional(readOnly = true)
    public CustomResponse<List<Building>> getAll(){
        return new CustomResponse<>(this.repository.findAll(),false,200,"Ok");
    }
    @Transactional(readOnly = true)
    public CustomResponse<Optional<Building>> getById(Long id){
        if (this.repository.existsById(id)){
            return new CustomResponse<>(this.repository.findById(id),false,200,"Ok");
        }
        return new CustomResponse<>(null,true,400,"Edificio no encotrado");
    }
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Building> insert(Building building){
        return new CustomResponse<>(this.repository.save(building),false,200,"Ok");
    }
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Building> update(Building building){
        if (this.repository.existsById(building.getId())){
            return new CustomResponse<>(this.repository.saveAndFlush(building),false,200,"Ok");
        }
        return new CustomResponse<>(null,true,400,"Edificio no encontrado");
    }
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Boolean> delete(Long id){
        if (this.repository.existsById(id)){
            try{
                this.repository.deleteById(id);
                return new CustomResponse<>(true,false,200,"Ok");
            }catch (Exception e){
                return new CustomResponse<>(false,true,500,"Error en la base de datos");
            }
        }
        return new CustomResponse<>(false,true,400,"Edificio no encontrado");
    }
}
