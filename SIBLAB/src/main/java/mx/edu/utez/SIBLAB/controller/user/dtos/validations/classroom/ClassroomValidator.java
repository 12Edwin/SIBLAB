package mx.edu.utez.SIBLAB.controller.user.dtos.validations.classroom;

import mx.edu.utez.SIBLAB.models.classroom.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ClassroomValidator implements ConstraintValidator<ValidClassroom,String> {
    @Autowired
    private GroupRepository repository;
    @Override
    public void initialize(ValidClassroom constraintAnnotation){
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintContext){
        if (value == null){
            return true;
        }
        return this.repository.existsByName(value);
    }
}
