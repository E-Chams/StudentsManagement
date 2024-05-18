package ma.xproce.studentsmanaging.dao.repositories;

import ma.xproce.studentsmanaging.dao.entities.ClassSession;
import ma.xproce.studentsmanaging.dao.entities.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Integer> {
    Page<Student> findByFnameContainingOrLnameContains(String fname,String lname,Pageable pageable);
}
