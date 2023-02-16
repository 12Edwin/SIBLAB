package mx.edu.utez.SIBLAB.controller.user.dtos.validations.code;

import mx.edu.utez.SIBLAB.models.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CodeValidator implements ConstraintValidator<ValidCode,String> {
    @Autowired
    private UserRepository repository;

    @Override
    public void initialize(ValidCode constraintAnnotation){
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintContext){
        if (value == null){
            return true;
        }
        return !this.repository.existsByCode(value);
    }
}
