package com.jobportal.jobportal.repository;

import com.jobportal.jobportal.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByEmail(String email);
}

// public class StudentRepository {
    
// }
