package ru.egartech.Exceptions;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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

}