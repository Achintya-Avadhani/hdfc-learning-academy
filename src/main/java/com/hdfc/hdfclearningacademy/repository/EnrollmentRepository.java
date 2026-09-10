package com.hdfc.hdfclearningacademy.repository;


import com.hdfc.hdfclearningacademy.entity.Enrollment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class EnrollmentRepository {
    private final Map<Integer, Enrollment> enrollmentMap =
            new HashMap<>();

    private Integer nextId = 1;

    public Enrollment save(Enrollment enrollment) {

        if (enrollment.getEnrollmentId() == null) {
            enrollment.setEnrollmentId(nextId++);
        }

        enrollmentMap.put(
                enrollment.getEnrollmentId(),
                enrollment
        );

        return enrollment;
    }

    public Enrollment findById(Integer id) {

        return enrollmentMap.get(id);
    }

    public List<Enrollment> findAll() {

        return new ArrayList<>(enrollmentMap.values());
    }

    public void deleteById(Integer id) {

        enrollmentMap.remove(id);
    }
}
