package ma.xproce.studentsmanaging.dao.repositories;

import ma.xproce.studentsmanaging.dao.entities.Professor;
import ma.xproce.studentsmanaging.dao.entities.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
    Page<Professor> findByLnameContaining(String keyword, Pageable pageable);
    @Query("SELECT b FROM Professor b ORDER BY b.id DESC")
    List<Professor> findTop3ByOrderByIdDesc(Pageable pageable);
}
