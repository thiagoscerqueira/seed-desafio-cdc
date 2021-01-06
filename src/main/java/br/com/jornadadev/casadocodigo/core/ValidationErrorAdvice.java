package br.com.jornadadev.casadocodigo.core;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ValidationErrorAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ValidationErrorDto handleArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<ObjectError> globalErrors = exception.getGlobalErrors();
        List<FieldError> fieldErrors = exception.getFieldErrors();

        ValidationErrorDto validationErrorDto = new ValidationErrorDto();

        globalErrors.forEach(error -> validationErrorDto.addGlobalError(error.getDefaultMessage()));
        fieldErrors.forEach(error -> validationErrorDto.addFieldError(new FieldErrorOutputDto(error.getField(),
                error.getDefaultMessage())));

        return validationErrorDto;
    }
}
