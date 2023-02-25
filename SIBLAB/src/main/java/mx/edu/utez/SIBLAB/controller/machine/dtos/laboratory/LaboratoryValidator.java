package mx.edu.utez.SIBLAB.controller.machine.dtos.laboratory;

import mx.edu.utez.SIBLAB.models.building.Building;
import mx.edu.utez.SIBLAB.models.building.BuildingRepository;
import mx.edu.utez.SIBLAB.models.laboratory.Laboratory;
import mx.edu.utez.SIBLAB.models.laboratory.LaboratoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LaboratoryValidator implements ConstraintValidator<ValidLaboratory, Laboratory> {

    @Autowired
    private LaboratoryRepository repository;

    @Override
    public void initialize(ValidLaboratory constraintAnnotation){
    }

    @Override
    public boolean isValid(Laboratory value, ConstraintValidatorContext constraintContext){
        if (value == null){
            return true;
        }
        return this.repository.existsById(value.getId());
    }
}
