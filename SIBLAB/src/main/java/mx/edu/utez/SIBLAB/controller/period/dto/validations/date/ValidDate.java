package mx.edu.utez.SIBLAB.controller.period.dto.validations.date;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Constraint(validatedBy = DateValidator.class)
@Documented
public @interface ValidDate {
    String message() default "{mx.edu.utez.SIBLAB.controller.user.dtos.validations.date.ValidDate.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String dateFormat();
}
