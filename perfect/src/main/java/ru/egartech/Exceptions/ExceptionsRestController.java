package ru.egartech.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.thymeleaf.exceptions.TemplateAssertionException;

import java.text.ParseException;

@RestControllerAdvice
public class ExceptionsRestController {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseError MethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return new ResponseError(e.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ParseException.class)
    public ResponseError ParseException(ParseException e) {
        return new ResponseError(e.getMessage(), HttpStatus.NOT_FOUND);
    }

}
