package com.pbl.controller.exceptionHandler;

import com.pbl.exception.QuestionNotAssignedException;
import com.pbl.exception.QuestionNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<String> handleException(HttpRequestMethodNotSupportedException httpRequestMethodNotSupportedException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(httpRequestMethodNotSupportedException.getMessage());
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleException(NoSuchElementException noSuchElementExceptionHandler) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(noSuchElementExceptionHandler.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleException(HttpMessageNotReadableException httpMessageNotReadableException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(httpMessageNotReadableException.getMessage());
    }

    @ExceptionHandler(QuestionNotAssignedException.class)
    public ResponseEntity<String> handleException(QuestionNotAssignedException questionNotAssignedException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(questionNotAssignedException.getMessage());
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<String> handleException(HttpMediaTypeNotSupportedException httpMediaTypeNotSupportedException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(httpMediaTypeNotSupportedException.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleException(MethodArgumentTypeMismatchException methodArgumentTypeMismatchException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(methodArgumentTypeMismatchException.getMessage());
    }

    @ExceptionHandler(QuestionNotFoundException.class)
    public ResponseEntity<String> handleException(QuestionNotFoundException questionNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(questionNotFoundException.getMessage());
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleException(NullPointerException nullPointerException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(nullPointerException.getMessage());
    }
}