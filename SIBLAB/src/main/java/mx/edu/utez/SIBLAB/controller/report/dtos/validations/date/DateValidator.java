package mx.edu.utez.SIBLAB.controller.report.dtos.validations.date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateValidator implements ConstraintValidator<ValidDate, String> {
    private String dateFormat;
    @Override
    public void initialize(ValidDate constraintAnnotation){
        this.dateFormat = constraintAnnotation.dateFormat();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintContext){
        if (value == null){
            return false;
        }
        DateFormat sdf = new SimpleDateFormat(this.dateFormat);
        sdf.setLenient(false);
        try {
            sdf.parse(value);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}
