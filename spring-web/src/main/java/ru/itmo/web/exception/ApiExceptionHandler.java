package ru.itmo.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.itmo.web.dto.ErrorDto;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorDto handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return new ErrorDto(
                ex.getStatusCode().toString(),
                ex.getMessage()
        );
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(Exception.class)
    public ErrorDto handleException(Exception ex) {
        return new ErrorDto(
                HttpStatus.CONFLICT.toString(),
                ex.getMessage()
        );
    }

    @ExceptionHandler(CityNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleCityNotFoundException(CityNotFoundException ex) {
        return new ErrorDto(
                HttpStatus.BAD_REQUEST.toString(),
                "City with id: " + ex.getCityId() + " not found"
        );
    }
}
