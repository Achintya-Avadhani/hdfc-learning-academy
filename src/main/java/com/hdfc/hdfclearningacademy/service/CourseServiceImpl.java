package com.hdfc.hdfclearningacademy.service;

import com.hdfc.hdfclearningacademy.dto.CourseRequestDto;
import com.hdfc.hdfclearningacademy.dto.CourseResponseDto;
import com.hdfc.hdfclearningacademy.entity.Course;
import com.hdfc.hdfclearningacademy.exception.CourseNotFoundException;
import com.hdfc.hdfclearningacademy.mapper.CourseMapper;
import com.hdfc.hdfclearningacademy.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    @Override
    public CourseResponseDto createCourse(
            CourseRequestDto dto) {

        Course course =
                CourseMapper.toEntity(dto, null);

        Course savedCourse =
                courseRepository.save(course);

        return CourseMapper.toResponse(savedCourse);
    }

    @Override
    public CourseResponseDto getCourseById(Integer id) {

        Course course =
                courseRepository.findById(id);

        if (course == null) {
            throw new CourseNotFoundException(
                    "Course not found with id: " + id
            );
        }

        return CourseMapper.toResponse(course);
    }

    @Override
    public List<CourseResponseDto> getAllCourses() {

        return courseRepository.findAll()
                .stream()
                .map(CourseMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CourseResponseDto updateCourse(
            Integer id,
            CourseRequestDto dto) {

        Course existingCourse =
                courseRepository.findById(id);

        if (existingCourse == null) {
            throw new CourseNotFoundException(
                    "Course not found with id: " + id
            );
        }

        existingCourse.setCourseName(
                dto.getCourseName());

        existingCourse.setTrainerName(
                dto.getTrainerName());

        existingCourse.setDurationInDays(
                dto.getDurationInDays());

        existingCourse.setMaxCapacity(
                dto.getMaxCapacity());

        existingCourse.setFees(
                dto.getFees());

        courseRepository.save(existingCourse);

        return CourseMapper.toResponse(existingCourse);
    }

    @Override
    public void deleteCourse(Integer id) {

        Course course =
                courseRepository.findById(id);

        if (course == null) {
            throw new CourseNotFoundException(
                    "Course not found with id: " + id
            );
        }

        courseRepository.deleteById(id);
    }

    @Override
    public List<CourseResponseDto> getCoursesByTrainer(
            String trainerName) {

        return courseRepository.findAll()
                .stream()
                .filter(course ->
                        course.getTrainerName()
                                .equalsIgnoreCase(trainerName))
                .map(CourseMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CourseResponseDto> getCoursesByFeesLessThan(
            Double amount) {

        return courseRepository.findAll()
                .stream()
                .filter(course ->
                        course.getFees() < amount)
                .map(CourseMapper::toResponse)
                .collect(Collectors.toList());
    }
}
