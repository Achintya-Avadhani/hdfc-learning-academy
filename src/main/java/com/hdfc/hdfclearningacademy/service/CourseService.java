package com.hdfc.hdfclearningacademy.service;

import com.hdfc.hdfclearningacademy.dto.CourseRequestDto;
import com.hdfc.hdfclearningacademy.dto.CourseResponseDto;

import java.util.List;

public interface CourseService {
    CourseResponseDto createCourse(CourseRequestDto dto);

    CourseResponseDto getCourseById(Integer id);

    List<CourseResponseDto> getAllCourses();

    CourseResponseDto updateCourse(
            Integer id,
            CourseRequestDto dto);

    void deleteCourse(Integer id);

    List<CourseResponseDto> getCoursesByTrainer(
            String trainerName);

    List<CourseResponseDto> getCoursesByFeesLessThan(
            Double amount);
}
