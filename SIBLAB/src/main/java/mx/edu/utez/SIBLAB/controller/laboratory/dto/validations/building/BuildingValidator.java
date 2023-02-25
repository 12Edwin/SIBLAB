package mx.edu.utez.SIBLAB.controller.laboratory.dto.validations.building;

import mx.edu.utez.SIBLAB.models.building.Building;
import mx.edu.utez.SIBLAB.models.building.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BuildingValidator implements ConstraintValidator<ValidBuilding,Building> {

    @Autowired
    private BuildingRepository repository;

    @Override
    public void initialize(ValidBuilding constraintAnnotation){
    }

    @Override
    public boolean isValid(Building value, ConstraintValidatorContext constraintContext){
        if (value == null){
            return true;
        }
        return this.repository.existsById(value.getId());
    }
}
