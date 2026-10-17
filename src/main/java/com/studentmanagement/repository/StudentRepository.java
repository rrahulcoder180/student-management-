
package com.studentmanagement.repository;

import com.studentmanagement.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository
        extends JpaRepository<Student, Long> {
}

