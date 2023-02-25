package mx.edu.utez.SIBLAB.controller.classroom.dtos.validations.period;

import mx.edu.utez.SIBLAB.models.classroom.GroupRepository;
import mx.edu.utez.SIBLAB.models.period.Period;
import mx.edu.utez.SIBLAB.models.period.PeriodRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PeriodValidator implements ConstraintValidator<ValidPeriod, Period> {

    @Autowired
    private PeriodRepository repository;

    @Override
    public void initialize(ValidPeriod constrainAnnotation){
    }

    @Override
    public boolean isValid(Period value, ConstraintValidatorContext constraintContext){
        if (value == null){
            return true;
        }
        return repository.existsById(value.getId());
    }
}
