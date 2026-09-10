package com.hdfc.hdfclearningacademy.mapper;

import com.hdfc.hdfclearningacademy.dto.CourseRequestDto;
import com.hdfc.hdfclearningacademy.dto.CourseResponseDto;
import com.hdfc.hdfclearningacademy.entity.Course;

public class CourseMapper {
    public static Course toEntity(
            CourseRequestDto dto,
            Integer courseId) {

        return new Course(
                courseId,
                dto.getCourseName(),
                dto.getTrainerName(),
                dto.getDurationInDays(),
                dto.getMaxCapacity(),
                dto.getFees()
        );
    }

    public static CourseResponseDto toResponse(Course course) {

        return new CourseResponseDto(
                course.getCourseId(),
                course.getCourseName(),
                course.getTrainerName(),
                course.getDurationInDays(),
                course.getMaxCapacity(),
                course.getFees()
        );
    }

}
