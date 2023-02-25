package mx.edu.utez.SIBLAB.controller.period.dto.validations.student;

import mx.edu.utez.SIBLAB.models.user.User;
import mx.edu.utez.SIBLAB.models.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class UserValidator implements ConstraintValidator<ValidUser,User> {
    @Autowired
    private UserRepository repository;

    @Override
    public void initialize(ValidUser constraintAnnotation){
    }

    @Override
    public boolean isValid(User value, ConstraintValidatorContext constraintContext){
        if (!this.repository.existsById(value.getId())){
            return false;
        }
        Optional<User> user = this.repository.findById(value.getId());

        return user.get().getRole().equals("Teacher");
    }
}
