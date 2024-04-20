package ma.xproce.studentsmanaging.dao.repositories;

import ma.xproce.studentsmanaging.dao.entities.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
}
