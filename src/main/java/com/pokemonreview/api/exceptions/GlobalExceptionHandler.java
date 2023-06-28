package com.pokemonreview.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
/*  @ControllerAdvice là một chú thích (annotation) trong Spring Framework
 được sử dụng để xác định một lớp trong ứng dụng như một global exception handler
  (bộ xử lý ngoại lệ toàn cục) cho các controller khác.*/
public class GlobalExceptionHandler {
    @ExceptionHandler(PokemonNotFoundException.class)
    public ResponseEntity<ErrorObject> handlePokemonNotFoundExcption(PokemonNotFoundException ex, WebRequest webRequest){
        ErrorObject errorObject = new ErrorObject();

        errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorObject.setMessage(ex.getMessage());
        errorObject.setTimestamp(new Date());
        return new  ResponseEntity<ErrorObject>(errorObject,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ReviewNotFoundException.class)
    public ResponseEntity<ErrorObject> handleReviewNotFoundExcption(ReviewNotFoundException ex, WebRequest webRequest){
        ErrorObject errorObject = new ErrorObject();

        errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorObject.setMessage(ex.getMessage());
        errorObject.setTimestamp(new Date());
        return new  ResponseEntity<ErrorObject>(errorObject,HttpStatus.NOT_FOUND);
    }
}