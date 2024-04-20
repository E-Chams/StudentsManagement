package ma.xproce.studentsmanaging.dao.repositories;

import ma.xproce.studentsmanaging.dao.entities.ClassSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassSessionRepository extends JpaRepository<ClassSession, Integer> {
    List<ClassSession> findStudentById(Integer studentId);

}
