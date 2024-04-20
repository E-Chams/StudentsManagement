package ma.xproce.studentsmanaging.service;

import ma.xproce.studentsmanaging.dao.entities.Course;
import ma.xproce.studentsmanaging.dao.entities.Professor;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProfessorManager {
    public Professor addProfessor(Professor professor);

    public boolean deleteProfessor(Professor professor);

    Page<Professor> getAllProfessors(int page, int taille);
}
