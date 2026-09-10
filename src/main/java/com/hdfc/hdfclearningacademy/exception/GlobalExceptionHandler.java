package com.hdfc.hdfclearningacademy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler{
    @ExceptionHandler(CourseNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleCourseNotFound(
            CourseNotFoundException exception) {

        return exception.getMessage();
    }

    @ExceptionHandler(EnrollmentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleEnrollmentNotFound(
            EnrollmentNotFoundException exception) {

        return exception.getMessage();
    }

    @ExceptionHandler(DuplicateEnrollmentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleDuplicateEnrollment(
            DuplicateEnrollmentException exception) {

        return exception.getMessage();
    }

    @ExceptionHandler(CourseCapacityFullException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleCourseCapacityFull(
            CourseCapacityFullException exception) {

        return exception.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleValidationException(
            MethodArgumentNotValidException exception) {

        return "Validation failed";
    }

}
