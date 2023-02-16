package mx.edu.utez.SIBLAB.controller.report.dtos.validations.teacher;

import mx.edu.utez.SIBLAB.models.user.User;
import mx.edu.utez.SIBLAB.models.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class TeacherValidator implements ConstraintValidator<ValidTeacher, Long> {
    @Autowired
    private UserRepository repository;

    @Override
    public void initialize(ValidTeacher constraintAnnotation){
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext constraintContext){
        if(value == null){
            return false;
        }
        if (!this.repository.existsById(value)){
            return false;
        }
        Optional <User> user = this.repository.findById(value);

        return user.get().getRole().equals("Teacher");
    }
}
