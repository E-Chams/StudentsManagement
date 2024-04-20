package ma.xproce.studentsmanaging.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import ma.xproce.studentsmanaging.dao.entities.Course;
import ma.xproce.studentsmanaging.dao.entities.Professor;
import ma.xproce.studentsmanaging.dao.entities.Student;
import ma.xproce.studentsmanaging.dao.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Properties;
@Service
public class ProfessorManagerService implements ProfessorManager{

    @Autowired
    private ProfessorRepository professorRepository;
    @Override
    public Professor addProfessor(Professor professor) {
        if (!professorRepository.existsById(professor.getId())) {
            return professorRepository.save(professor);
        } else {
            System.out.println("the creator already exist");
            return null;
        }
    }

    @Override
    public boolean deleteProfessor(Professor professor) {
        if (professorRepository.existsById(professor.getId())) {
            professorRepository.deleteById(professor.getId());
            return true;
        } else {
            System.out.println("the professor doesnt exist");
            return false;
        }
    }

    @Override
    public Page<Professor> getAllProfessors(int page, int taille) {
        return professorRepository.findAll(PageRequest.of(page, taille));
    }

    @Override
    public Professor updateProfessor(Professor professor) {
        return professorRepository.save(professor);
    }

    @Override
    public Professor getProfessorById(Integer id){return professorRepository.findById(id).orElse(null);}
}
