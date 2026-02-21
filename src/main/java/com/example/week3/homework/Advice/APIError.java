package com.example.week3.homework.Advice;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import java.util.List;

@Builder
@Getter
@Setter
public class APIError {
    private  HttpStatus httpStatus;
    private  String message;
    private  String errorRecordedTime;
    private List<String> subErrors;
}