package ma.xproce.studentsmanaging.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import ma.xproce.studentsmanaging.dao.entities.Course;
import ma.xproce.studentsmanaging.dao.entities.Professor;
import ma.xproce.studentsmanaging.dao.entities.Student;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProfessorManager {
    public Professor addProfessor(Professor professor);

    public boolean deleteProfessor(Professor professor);

    Page<Professor> getAllProfessors(int page, int taille);
    public Professor updateProfessor(Professor professor);
    public  Professor getProfessorById(Integer id);
    public Page<Professor> searchProfessor(String keyword, int page , int taille);
    public Integer getProfessorsCount();
    public List<Professor> getLastThreeProfessors();
}
