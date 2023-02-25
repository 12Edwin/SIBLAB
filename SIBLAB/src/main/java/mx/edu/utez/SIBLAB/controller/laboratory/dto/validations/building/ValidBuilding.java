package mx.edu.utez.SIBLAB.controller.laboratory.dto.validations.building;

import mx.edu.utez.SIBLAB.controller.classroom.dtos.validations.period.PeriodValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Constraint(validatedBy = BuildingValidator.class)
@Documented
public @interface ValidBuilding {
    String message() default "{mx.edu.utez.SIBLAB.controller.laboratory.dtos.validations.building.ValidBuilding.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
