package mx.edu.utez.SIBLAB.controller.report.dtos.validations.teacher;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Constraint(validatedBy = TeacherValidator.class)
@Documented
public @interface ValidTeacher {
    String message() default "{mx.edu.utez.SIBLAB.controller.user.dtos.validations.teacher.ValidTeacher.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
