package com.hdfc.hdfclearningacademy.service;

import com.hdfc.hdfclearningacademy.dto.CourseResponseDto;
import com.hdfc.hdfclearningacademy.dto.EnrollmentRequestDto;
import com.hdfc.hdfclearningacademy.dto.EnrollmentResponseDto;

import java.util.List;

public interface EnrollmentService {
    EnrollmentResponseDto enrollEmployee(
            EnrollmentRequestDto dto);

    List<EnrollmentResponseDto> getAllEnrollments();

    EnrollmentResponseDto getEnrollmentById(Integer id);

    EnrollmentResponseDto cancelEnrollment(Integer id);

    EnrollmentResponseDto completeEnrollment(Integer id);

    List<EnrollmentResponseDto> getEnrollmentsByStatus(
            String status);

    List<EnrollmentResponseDto> getEnrollmentsByEmployeeId(
            Integer employeeId);

    long getEnrollmentCount();

    CourseResponseDto getMostPopularCourse();

}
