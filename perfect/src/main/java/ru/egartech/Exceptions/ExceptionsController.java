package ru.egartech.Exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.thymeleaf.exceptions.TemplateInputException;

import java.text.ParseException;

@ControllerAdvice
public class ExceptionsController {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseError handleException(MethodArgumentNotValidException e) {
        System.out.println("Ошибка : " + e.getMessage());
        return new ResponseError(e.getMessage(), HttpStatus.PAYMENT_REQUIRED);
    }
    @ExceptionHandler(ParseException.class)
    public ResponseError ParseException(ParseException e) {
        System.out.println("Ошибка : " + e.getMessage());
        return new ResponseError(e.getMessage(), HttpStatus.PAYMENT_REQUIRED);
    }
    @ExceptionHandler(TemplateInputException.class)
    public ResponseError TemplateInputException(ParseException e) {
        System.out.println("Ошибка : " + e.getMessage());
        return new ResponseError(e.getMessage(), HttpStatus.PAYMENT_REQUIRED);
    }
    @ExceptionHandler(NullPointerException.class)
    public ResponseError NullPointerException(NullPointerException e) {
        System.out.println("Ошибка : " + e.getMessage());
        return new ResponseError(e.getMessage(), HttpStatus.PAYMENT_REQUIRED);
    }
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, WebRequest request, HttpStatus status) {
        String str = "Tакой страницы здесь нет.";
        return new ResponseEntity<>(str, new HttpHeaders(), status);
    }
}