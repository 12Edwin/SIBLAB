package mx.edu.utez.SIBLAB.controller.user.dtos.validations.noNumbers;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static org.hibernate.cache.spi.support.SimpleTimestamper.next;

public class NoNumbersValidator implements ConstraintValidator<ValidNoNumbers,String> {
    @Override
    public void initialize(ValidNoNumbers constraintAnnotation){
    }

    @Override
    public boolean  isValid(String value, ConstraintValidatorContext constraintContext){
        if (value == null){
            return true;
        }
        String letter = "";
        for(int i = 0; i < value.length(); i++){
            try {
                letter = value.charAt(i)+"";
                Integer.parseInt(letter);
                return false;
            }catch (Exception e){

            }
        }
        return true;
    }
}
