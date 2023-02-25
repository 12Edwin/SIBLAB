package mx.edu.utez.SIBLAB.controller.attachment.dto.validations.status;

import mx.edu.utez.SIBLAB.models.status.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StatusValidator implements ConstraintValidator<ValidStatus,String> {
    @Autowired
    private StatusRepository repository;
    @Override
    public void initialize(ValidStatus constraintAnnotation){
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintContext){
        if (value == null){
            return true;
        }
        return this.repository.existsByName(value);
    }
}
