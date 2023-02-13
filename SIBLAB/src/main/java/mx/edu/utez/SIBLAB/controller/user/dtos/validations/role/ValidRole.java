package mx.edu.utez.SIBLAB.controller.user.dtos.validations.role;

import mx.edu.utez.SIBLAB.controller.user.dtos.validations.noNumbers.NoNumbersValidator;

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
@Constraint(validatedBy = RoleValidator.class)
@Documented
public @interface ValidRole {
    String message() default "{mx.edu.utez.SIBLAB.controller.user.dtos.validations.role.ValidRole}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
