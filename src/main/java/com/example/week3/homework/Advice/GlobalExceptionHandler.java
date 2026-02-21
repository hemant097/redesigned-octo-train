package com.example.week3.homework.Advice;

import com.example.week3.homework.Exceptions.DuplicateResourceException;
import com.example.week3.homework.Exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APIError> resourceNotFound(ResourceNotFoundException rnfe) {

        APIError apiError = createAPIErrorObject(rnfe.getMessage());
        apiError.setHttpStatus(HttpStatus.NOT_FOUND);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIError> internalServerError(MethodArgumentNotValidException manve) {

        //getting all the binding errors from the exception and converting it to List<String> using stream
        List<String> errorList = manve.getBindingResult()
                .getAllErrors()
                .stream().map(error->error.getDefaultMessage())
                .collect(Collectors.toList());


        APIError apiError = createAPIErrorObject(manve.getMessage());
        apiError.setSubErrors(errorList);
        apiError.setHttpStatus(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<APIError> duplicateResource(DuplicateResourceException dre) {

        APIError apiError = createAPIErrorObject(dre.getMessage());
        apiError.setHttpStatus(HttpStatus.NOT_ACCEPTABLE);

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(apiError);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIError> internalServerError(Exception exception) {

        APIError apiError = createAPIErrorObject(exception.getMessage());
        apiError.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    private APIError createAPIErrorObject(String exceptionMessage){

        String currentDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm"));
        return APIError.builder()
                .message(exceptionMessage)
                .errorRecordedTime(currentDateTime)
                .build();
    }
}