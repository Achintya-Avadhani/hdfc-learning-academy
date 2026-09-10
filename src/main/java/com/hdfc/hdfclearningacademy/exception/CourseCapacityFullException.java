package com.hdfc.hdfclearningacademy.exception;

public class CourseCapacityFullException extends RuntimeException {
    public CourseCapacityFullException(String message) {
        super(message);
    }
}
