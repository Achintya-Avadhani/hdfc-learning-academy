package com.hdfc.hdfclearningacademy.mapper;

import com.hdfc.hdfclearningacademy.dto.EnrollmentRequestDto;
import com.hdfc.hdfclearningacademy.dto.EnrollmentResponseDto;
import com.hdfc.hdfclearningacademy.entity.Enrollment;

import java.time.LocalDate;

public class EnrollmentMapper {

    public static Enrollment toEntity(
            EnrollmentRequestDto dto,
            Integer enrollmentId) {

        return new Enrollment(
                enrollmentId,
                dto.getEmployeeId(),
                dto.getEmployeeName(),
                dto.getCourseId(),
                LocalDate.now(),
                "ENROLLED"
        );
    }

    public static EnrollmentResponseDto toResponse(
            Enrollment enrollment) {

        return new EnrollmentResponseDto(
                enrollment.getEnrollmentId(),
                enrollment.getEmployeeId(),
                enrollment.getEmployeeName(),
                enrollment.getCourseId(),
                enrollment.getEnrollmentDate(),
                enrollment.getStatus()
        );
    }
}
