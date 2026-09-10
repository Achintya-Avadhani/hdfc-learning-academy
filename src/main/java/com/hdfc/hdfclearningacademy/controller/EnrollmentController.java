package com.hdfc.hdfclearningacademy.controller;


import com.hdfc.hdfclearningacademy.dto.EnrollmentRequestDto;
import com.hdfc.hdfclearningacademy.dto.EnrollmentResponseDto;
import com.hdfc.hdfclearningacademy.service.EnrollmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EnrollmentResponseDto enrollEmployee(
            @Valid @RequestBody EnrollmentRequestDto dto) {

        return enrollmentService.enrollEmployee(dto);
    }

    @GetMapping
    public List<EnrollmentResponseDto> getAllEnrollments() {

        return enrollmentService.getAllEnrollments();
    }

    @GetMapping("/{id}")
    public EnrollmentResponseDto getEnrollmentById(
            @PathVariable Integer id) {

        return enrollmentService.getEnrollmentById(id);
    }

    @PutMapping("/{id}/cancel")
    public EnrollmentResponseDto cancelEnrollment(
            @PathVariable Integer id) {

        return enrollmentService.cancelEnrollment(id);
    }

    @PutMapping("/{id}/complete")
    public EnrollmentResponseDto completeEnrollment(
            @PathVariable Integer id) {

        return enrollmentService.completeEnrollment(id);
    }

    @GetMapping("/status/{status}")
    public List<EnrollmentResponseDto> getByStatus(
            @PathVariable String status) {

        return enrollmentService.getEnrollmentsByStatus(status);
    }

    @GetMapping("/employee/{employeeId}")
    public List<EnrollmentResponseDto> getByEmployeeId(
            @PathVariable Integer employeeId) {

        return enrollmentService.getEnrollmentsByEmployeeId(employeeId);
    }

}
