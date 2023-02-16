package mx.edu.utez.SIBLAB.controller.user.dtos.validations.email;

import mx.edu.utez.SIBLAB.models.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;



public class EmailValidator implements ConstraintValidator<ValidEmail,String> {
    @Autowired
    private UserRepository repository;

    @Override
    public void initialize(ValidEmail constraintAnnotation){
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintContext){
        if (value == null){
            return true;
        }
        if (this.repository.existsByEmail(value)){
            return false;
        }
        return value.split("@").length > 1;
    }
}


