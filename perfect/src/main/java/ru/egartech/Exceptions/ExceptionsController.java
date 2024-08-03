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

import java.text.ParseException;

@ControllerAdvice
public class ExceptionsController {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleException(MethodArgumentNotValidException e) {
        System.out.println("Некорректная форма ввода : " + e.getMessage());
        return "redirect:/lenta";
    }
    @ExceptionHandler(ParseException.class)
    public String ParseException(ParseException e) {
        System.out.println("Введите дату : " + e.getMessage());
        return "redirect:/lenta";
    }
    @ExceptionHandler(NullPointerException.class)
    public String NullPointerException(NullPointerException e) {
        System.out.println("Авторизуйтесь : " + e.getMessage());
        return "redirect:/lenta";
    }
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, WebRequest request, HttpStatus status) {
        String str = "Tакой страницы здесь нет.";
        return new ResponseEntity<>(str, new HttpHeaders(), status);
    }
}