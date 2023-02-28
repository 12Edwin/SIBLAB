package mx.edu.utez.SIBLAB.controller.report.dtos.validations.attachment;

import mx.edu.utez.SIBLAB.models.attachment.Attachment;
import mx.edu.utez.SIBLAB.models.attachment.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AttachmentValidator implements ConstraintValidator<ValidAttachment, Attachment> {

    @Autowired
    private AttachmentRepository repository;

    @Override
    public void initialize(ValidAttachment constraintAnnotation){
    }

    @Override
    public boolean isValid(Attachment value, ConstraintValidatorContext constraintContext){
        if (value == null){
            return true;
        }
        return this.repository.existsById(value.getId());
    }
}
