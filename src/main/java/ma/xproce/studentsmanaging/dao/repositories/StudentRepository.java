package ma.xproce.studentsmanaging.dao.repositories;

import ma.xproce.studentsmanaging.dao.entities.ClassSession;
import ma.xproce.studentsmanaging.dao.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Integer> {

}
