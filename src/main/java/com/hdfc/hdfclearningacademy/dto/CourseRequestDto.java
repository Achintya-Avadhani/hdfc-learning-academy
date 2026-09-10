package com.hdfc.hdfclearningacademy.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CourseRequestDto {

    @NotBlank
    private String courseName;

    @NotBlank
    private String trainerName;

    @NotNull
    @Positive
    private Integer durationInDays;

    @NotNull
    @Positive
    private Integer maxCapacity;

    @NotNull
    @Positive
    private Double fees;
}
