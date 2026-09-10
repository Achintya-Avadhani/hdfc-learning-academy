package com.hdfc.hdfclearningacademy.controller;


import com.hdfc.hdfclearningacademy.dto.CourseRequestDto;
import com.hdfc.hdfclearningacademy.dto.CourseResponseDto;
import com.hdfc.hdfclearningacademy.service.CourseService;
import com.hdfc.hdfclearningacademy.service.EnrollmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;
    private final EnrollmentService enrollmentService;


    @PostMapping("/courses")
    @ResponseStatus(HttpStatus.CREATED)
    public CourseResponseDto createCourse(
            @Valid @RequestBody CourseRequestDto dto) {

        return courseService.createCourse(dto);
    }

    @GetMapping("/courses/{id}")
    public CourseResponseDto getCourseById(
            @PathVariable Integer id) {

        return courseService.getCourseById(id);
    }


    @GetMapping("/courses")
    public List<CourseResponseDto> getAllCourses() {

        return courseService.getAllCourses();
    }

    @PutMapping("/courses/{id}")
    public CourseResponseDto updateCourse(
            @PathVariable Integer id,
            @Valid @RequestBody CourseRequestDto dto) {

        return courseService.updateCourse(id, dto);
    }

    @DeleteMapping("/courses/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCourse(
            @PathVariable Integer id) {

        courseService.deleteCourse(id);
    }

    @GetMapping("/courses/trainer/{trainerName}")
    public List<CourseResponseDto> getCoursesByTrainer(
            @PathVariable String trainerName) {

        return courseService.getCoursesByTrainer(trainerName);
    }

    @GetMapping("/courses/fees/{amount}")
    public List<CourseResponseDto> getCoursesByFees(
            @PathVariable Double amount) {

        return courseService.getCoursesByFeesLessThan(amount);
    }

    @GetMapping("/analytics/course-count")
    public long getCourseCount() {

        return courseService.getAllCourses().size();
    }

    @GetMapping("/analytics/enrollment-count")
    public long getEnrollmentCount() {

        return enrollmentService.getEnrollmentCount();
    }

    @GetMapping("/analytics/most-popular-course")
    public CourseResponseDto getMostPopularCourse() {

        return enrollmentService.getMostPopularCourse();
    }

}
