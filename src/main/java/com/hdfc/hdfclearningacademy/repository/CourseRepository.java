package com.hdfc.hdfclearningacademy.repository;

import com.hdfc.hdfclearningacademy.entity.Course;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CourseRepository {
    private final Map<Integer, Course> courseMap = new HashMap<>();

    private Integer nextId = 1;

    public Course save(Course course) {

        if (course.getCourseId() == null) {
            course.setCourseId(nextId++);
        }

        courseMap.put(course.getCourseId(), course);

        return course;
    }

    public Course findById(Integer id) {

        return courseMap.get(id);
    }

    public List<Course> findAll() {

        return new ArrayList<>(courseMap.values());
    }

    public void deleteById(Integer id) {

        courseMap.remove(id);
    }

}
