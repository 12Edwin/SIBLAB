package mx.edu.utez.SIBLAB.controller.user.dtos.validations.teacher;

import mx.edu.utez.SIBLAB.controller.user.dtos.validations.RepositoryValidation;
import mx.edu.utez.SIBLAB.models.user.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Optional;

public class TeacherValidator implements ConstraintValidator<ValidTeacher, User> {
    @Autowired
    private RepositoryValidation repository;

    @Override
    public void initialize(ValidTeacher constraintAnnotation){
    }

    @Override
    public boolean isValid(User value, ConstraintValidatorContext constraintContext){
        if (value == null){
            return true;
        }
        if (!this.repository.existsById(value.getId())){
            return false;
        }
        Optional <User> user = this.repository.findById(value.getId());

        return user.get().getRole().equals("Teacher");
    }
}
