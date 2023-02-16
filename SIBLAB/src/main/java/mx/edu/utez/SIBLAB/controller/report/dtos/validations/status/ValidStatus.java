package mx.edu.utez.SIBLAB.controller.report.dtos.validations.status;

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
@Constraint(validatedBy = StatusValidator.class)
@Documented
public @interface ValidStatus {
    String message() default "{mx.edu.utez.SIBLAB.controller.report.dtos.validations.status.ValidStatus.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
