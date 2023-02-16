package mx.edu.utez.SIBLAB.controller.report.dtos.validations.machine;

import mx.edu.utez.SIBLAB.models.machine.Machine;
import mx.edu.utez.SIBLAB.models.machine.MachineRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MachineValidator implements ConstraintValidator<ValidMachine,Machine> {
    @Autowired
    private MachineRepository repository;
    @Override
    public void initialize(ValidMachine constraintAnnotation){
    }
    @Override
    public boolean isValid(Machine value, ConstraintValidatorContext constraintContext){
        if (!this.repository.existsById(value.getId())){
            return false;
        }
        return true;
    }
}
