package ru.egartech.Exceptions;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
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

import java.io.IOException;
import java.text.ParseException;
@Slf4j
@ControllerAdvice
public class ExceptionsController {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseError MethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        return new ResponseError(e.getMessage(), HttpStatus.OK);
    }

    @ExceptionHandler(ParseException.class)
    public ResponseError ParseException(ParseException e) {
        log.error(e.getMessage(), e);
        return new ResponseError(e.getMessage(), HttpStatus.OK);
    }

}