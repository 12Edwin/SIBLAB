package mx.edu.utez.SIBLAB.controller.user.dtos.validations.classroom;

import mx.edu.utez.SIBLAB.controller.user.dtos.validations.code.CodeValidator;

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
@Constraint(validatedBy = ClassroomValidator.class)
@Documented
public @interface ValidClassroom {
    String message() default "{mx.edu.utez.SIBLAB.controller.user.dtos.validations.classroom.ValidClassroom}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
