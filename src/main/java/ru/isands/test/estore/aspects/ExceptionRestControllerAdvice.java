package ru.isands.test.estore.aspects;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.isands.test.estore.exceptions.EntityNotFOundException;
import ru.isands.test.estore.models.dto.ErrorDetails;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class ExceptionRestControllerAdvice {
    @ExceptionHandler(EntityNotFOundException.class)
    public ResponseEntity<ErrorDetails> exceptionENFEHandler(EntityNotFOundException exccc){
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMessage("Я посмотрел и не нашёл©  🤔");
        return ResponseEntity.badRequest().body(errorDetails);
    }

    //java.sql.SQLIntegrityConstraintViolationException
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ErrorDetails> exceptionSQLICVHandler(SQLIntegrityConstraintViolationException exccc){
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMessage("Нельзя добавить/удалить запись из-за ограничений внешних ключей 😤 ,"
                +" сначала добавьте/удалите запись в/из другую таблицу");
        return ResponseEntity.badRequest().body(errorDetails);
    }
}
