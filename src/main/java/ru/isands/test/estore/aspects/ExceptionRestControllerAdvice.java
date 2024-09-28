package ru.isands.test.estore.aspects;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.isands.test.estore.exceptions.EntityNotFOundException;
import ru.isands.test.estore.models.ErrorDetails;

@RestControllerAdvice
public class ExceptionRestControllerAdvice {
    @ExceptionHandler(EntityNotFOundException.class)
    public ResponseEntity<ErrorDetails> exceptionENFEHandler(EntityNotFOundException exccc){
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMessage("Я посмотрел и не нашёл©  🤔");
        return ResponseEntity.badRequest().body(errorDetails);
    }
}
