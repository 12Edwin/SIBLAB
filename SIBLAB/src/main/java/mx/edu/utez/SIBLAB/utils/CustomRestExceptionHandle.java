package mx.edu.utez.SIBLAB.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomRestExceptionHandle extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ){
        List<String> errors = new ArrayList();
        for (FieldError error: exception.getFieldErrors()){
            errors.add(error.getField() + ": "+ error.getDefaultMessage());
        }
        CustomResponse<Object> apiError = new CustomResponse<>(errors,true,400,"Validate Fields");
        return handleExceptionInternal(exception,apiError,headers,status,request);
    }
}
