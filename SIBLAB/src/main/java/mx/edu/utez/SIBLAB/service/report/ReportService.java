package mx.edu.utez.SIBLAB.service.report;

import mx.edu.utez.SIBLAB.controller.report.dtos.ReportDto;
import mx.edu.utez.SIBLAB.models.report.Report;
import mx.edu.utez.SIBLAB.models.report.ReportRepository;
import mx.edu.utez.SIBLAB.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReportService {
    @Autowired
    private ReportRepository repository;
    @Transactional(readOnly = true)
    public CustomResponse<List<Report>> getAll(){
        return new CustomResponse<>(this.repository.findAll(),false,200,"Ok");
    }
    @Transactional(readOnly = true)
    public CustomResponse<Optional<Report>> getById(Long id){
        if (this.repository.existsById(id))
            return new CustomResponse<>(this.repository.findById(id),false,200,"Ok");
        return new CustomResponse<>(null,true,400,"Reporte no encontrado");
    }
    @Transactional(readOnly = true)
    public CustomResponse<List<Report>> getAllByStudent(Long id){
        return new CustomResponse<>(this.repository.findAllReportsByStudent(id),false,200,"Ok");
    }
    @Transactional(readOnly = true)
    public CustomResponse<List<Report>> getByAllTeacher(Long id){
        return new CustomResponse<>(this.repository.findAllById_teacher(id),false,200,"Ok");
    }
    @Transactional(readOnly = true)
    public CustomResponse<List<Report>> getAllByStatus(String status){
        return new CustomResponse<>(this.repository.findAllByStatus(status),false,200,"Ok");
    }
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Report> insert(Report report){
        return new CustomResponse<>(this.repository.save(report),false,200,"Ok");
    }
    // Falta put
    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Boolean> changeStatus(Long id,String status){
        if (this.repository.existsById(id)){
            return new CustomResponse<>(this.repository.changeStatusById(id,status) == 1,false,200,"Ok");
        }
        return new CustomResponse<>(null,true,400,"Reporte no encotrado");
    }
}
