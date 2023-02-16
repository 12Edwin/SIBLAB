package mx.edu.utez.SIBLAB.controller.user.dtos.validations.role;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import mx.edu.utez.SIBLAB.models.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class RoleValidator implements ConstraintValidator<ValidRole,String> {
    @Autowired
    private RoleRepository repository;
    @Override
    public void initialize(ValidRole constraintAnnotation){
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintContext){
        if(value == null){
            return true;
        }
        return repository.existsByName(value);
    }
}
