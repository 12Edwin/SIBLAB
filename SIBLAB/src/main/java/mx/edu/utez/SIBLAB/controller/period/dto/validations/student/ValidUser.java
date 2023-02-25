package mx.edu.utez.SIBLAB.controller.period.dto.validations.student;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Constraint(validatedBy = UserValidator.class)
@Documented
public @interface ValidUser {
    String message() default "{mx.edu.utez.SIBLAB.controller.report.dtos.validations.student.ValidStudent.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
