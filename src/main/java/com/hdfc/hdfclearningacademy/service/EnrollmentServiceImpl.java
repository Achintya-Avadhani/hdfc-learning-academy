package com.hdfc.hdfclearningacademy.service;


import com.hdfc.hdfclearningacademy.dto.CourseResponseDto;
import com.hdfc.hdfclearningacademy.dto.EnrollmentRequestDto;
import com.hdfc.hdfclearningacademy.dto.EnrollmentResponseDto;
import com.hdfc.hdfclearningacademy.entity.Course;
import com.hdfc.hdfclearningacademy.entity.Enrollment;
import com.hdfc.hdfclearningacademy.exception.CourseCapacityFullException;
import com.hdfc.hdfclearningacademy.exception.CourseNotFoundException;
import com.hdfc.hdfclearningacademy.exception.DuplicateEnrollmentException;
import com.hdfc.hdfclearningacademy.exception.EnrollmentNotFoundException;
import com.hdfc.hdfclearningacademy.mapper.CourseMapper;
import com.hdfc.hdfclearningacademy.mapper.EnrollmentMapper;
import com.hdfc.hdfclearningacademy.repository.CourseRepository;
import com.hdfc.hdfclearningacademy.repository.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;

    private final CourseRepository courseRepository;

    @Override
    public EnrollmentResponseDto enrollEmployee(
            EnrollmentRequestDto dto) {


        Course course =
                courseRepository.findById(dto.getCourseId());

        if (course == null) {

            throw new CourseNotFoundException(
                    "Course not found with id: "
                            + dto.getCourseId()
            );
        }

        long enrolledCount =
                enrollmentRepository.findAll()
                        .stream()
                        .filter(enrollment ->
                                enrollment.getCourseId()
                                        .equals(dto.getCourseId()))
                        .filter(enrollment ->
                                enrollment.getStatus()
                                        .equals("ENROLLED"))
                        .count();

        if (enrolledCount >= course.getMaxCapacity()) {

            throw new CourseCapacityFullException(
                    "Course capacity is full"
            );
        }


        boolean alreadyEnrolled =
                enrollmentRepository.findAll()
                        .stream()
                        .anyMatch(enrollment ->
                                enrollment.getEmployeeId()
                                        .equals(dto.getEmployeeId())
                                        &&
                                        enrollment.getCourseId()
                                                .equals(dto.getCourseId())
                        );

        if (alreadyEnrolled) {

            throw new DuplicateEnrollmentException(
                    "Employee already enrolled in this course"
            );
        }

        Enrollment enrollment =
                EnrollmentMapper.toEntity(dto, null);

        Enrollment savedEnrollment =
                enrollmentRepository.save(enrollment);

        return EnrollmentMapper.toResponse(
                savedEnrollment);
    }

    @Override
    public List<EnrollmentResponseDto>
    getAllEnrollments() {

        return enrollmentRepository.findAll()
                .stream()
                .map(EnrollmentMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public EnrollmentResponseDto getEnrollmentById(
            Integer id) {

        Enrollment enrollment =
                enrollmentRepository.findById(id);

        if (enrollment == null) {

            throw new EnrollmentNotFoundException(
                    "Enrollment not found with id: " + id
            );
        }

        return EnrollmentMapper.toResponse(enrollment);
    }

    @Override
    public EnrollmentResponseDto cancelEnrollment(
            Integer id) {

        Enrollment enrollment =
                enrollmentRepository.findById(id);

        if (enrollment == null) {

            throw new EnrollmentNotFoundException(
                    "Enrollment not found with id: " + id
            );
        }

        enrollment.setStatus("CANCELLED");

        enrollmentRepository.save(enrollment);

        return EnrollmentMapper.toResponse(enrollment);
    }

    @Override
    public EnrollmentResponseDto completeEnrollment(
            Integer id) {

        Enrollment enrollment =
                enrollmentRepository.findById(id);

        if (enrollment == null) {

            throw new EnrollmentNotFoundException(
                    "Enrollment not found with id: " + id
            );
        }

        enrollment.setStatus("COMPLETED");

        enrollmentRepository.save(enrollment);

        return EnrollmentMapper.toResponse(enrollment);
    }

    @Override
    public List<EnrollmentResponseDto>
    getEnrollmentsByStatus(String status) {

        return enrollmentRepository.findAll()
                .stream()
                .filter(enrollment ->
                        enrollment.getStatus()
                                .equalsIgnoreCase(status))
                .map(EnrollmentMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<EnrollmentResponseDto>
    getEnrollmentsByEmployeeId(Integer employeeId) {

        return enrollmentRepository.findAll()
                .stream()
                .filter(enrollment ->
                        enrollment.getEmployeeId()
                                .equals(employeeId))
                .map(EnrollmentMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public long getEnrollmentCount() {

        return enrollmentRepository.findAll()
                .stream()
                .count();
    }
    @Override
    public CourseResponseDto getMostPopularCourse() {

        Map<Integer, Long> courseCounts =
                enrollmentRepository.findAll()
                        .stream()
                        .collect(Collectors.groupingBy(
                                Enrollment::getCourseId,
                                Collectors.counting()
                        ));

        Map.Entry<Integer, Long> popularCourse =
                courseCounts.entrySet()
                        .stream()
                        .max(Map.Entry.comparingByValue())
                        .orElse(null);

        if (popularCourse == null) {
            return null;
        }

        Course course =
                courseRepository.findById(
                        popularCourse.getKey()
                );

        return CourseMapper.toResponse(course);
    }
}
