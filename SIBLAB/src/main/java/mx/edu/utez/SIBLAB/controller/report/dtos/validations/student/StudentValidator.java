package mx.edu.utez.SIBLAB.controller.report.dtos.validations.student;

import mx.edu.utez.SIBLAB.models.user.User;
import mx.edu.utez.SIBLAB.models.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class StudentValidator implements ConstraintValidator<ValidStudent,User> {
    @Autowired
    private UserRepository repository;

    @Override
    public void initialize(ValidStudent constraintAnnotation){
    }

    @Override
    public boolean isValid(User value, ConstraintValidatorContext constraintContext){
        if (!this.repository.existsById(value.getId())){
            return false;
        }
        Optional<User> user = this.repository.findById(value.getId());

        return user.get().getRole().equals("Student");
    }
}
