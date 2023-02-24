package mx.edu.utez.SIBLAB.service.attachment;

import mx.edu.utez.SIBLAB.models.attachment.Attachment;
import mx.edu.utez.SIBLAB.models.attachment.AttachmentRepository;
import mx.edu.utez.SIBLAB.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class AttachmentService {

    @Autowired
    private AttachmentRepository repository;

    @Transactional(readOnly = true)
    public CustomResponse<List<Attachment>> getAll(){
        return new CustomResponse<>(this.repository.findAll(),false,200,"Ok");
    }

    @Transactional(readOnly = true)
    public CustomResponse<Optional<Attachment>> getById(Long id){
        if (this.repository.existsById(id)){
            return new CustomResponse<>(this.repository.findById(id),false,200,"Ok");
        }
        return new CustomResponse<>(null,true,400,"Archivos no encontrados");
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Attachment> insert(Attachment attachment){
        return new CustomResponse<>(this.repository.save(attachment),false,200,"Created");
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Attachment> update (Attachment attachment){
        if (this.repository.existsById(attachment.getId())){
            return new CustomResponse<>(this.repository.saveAndFlush(attachment),false,200,"Updated");
        }
        return new CustomResponse<>(null,true,400,"Archivos no encontrados");
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Boolean> changeStatus(Attachment attachment){
        if (this.repository.existsById(attachment.getId())){
            return new CustomResponse<>(this.repository.changeStatusById(attachment.getId(), attachment.getStatus()) == 1,false,200,"Changed");
        }
        return new CustomResponse<>(null,true,400,"Archivos no encontrados");
    }
}
