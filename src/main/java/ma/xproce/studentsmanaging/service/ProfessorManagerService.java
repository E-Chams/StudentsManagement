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

import java.util.Date;
import java.util.List;
import java.util.Properties;
@Service
public class ProfessorManagerService implements ProfessorManager{

    @Autowired
    private ProfessorRepository professorRepository;
    @Override
    public Professor addProfessor(Professor professor) {
        if (professorRepository.existsById(professor.getId()))
        {
            System.out.println("the professor already exist");
            return null;

        } else {
            professor.setCreatedAt(new Date());
            return professorRepository.save(professor);
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
        professor.setUpdatedAt(new Date());
        return professorRepository.save(professor);
    }

    @Override
    public Professor getProfessorById(Integer id){return professorRepository.findById(id).orElse(null);}

    @Override
    public Page<Professor> searchProfessor(String keyword, int page , int taille) {

        PageRequest pageable = PageRequest.of(page, taille);
        return  professorRepository.findByLnameContaining(keyword,pageable);

    }
    @Override
    public Integer getProfessorsCount() {
        return Math.toIntExact(professorRepository.count());
    }

}
