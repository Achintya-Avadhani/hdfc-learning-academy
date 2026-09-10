package com.hdfc.hdfclearningacademy.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class EnrollmentRequestDto {
    @NotNull
    @Positive
    private Integer employeeId;

    @NotBlank
    private String employeeName;

    @NotNull
    @Positive
    private Integer courseId;
}
