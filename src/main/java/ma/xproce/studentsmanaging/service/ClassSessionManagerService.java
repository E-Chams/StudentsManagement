package ma.xproce.studentsmanaging.service;

import ma.xproce.studentsmanaging.dao.entities.ClassSession;
import ma.xproce.studentsmanaging.dao.entities.Course;
import ma.xproce.studentsmanaging.dao.repositories.ClassSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClassSessionManagerService implements ClassSessionManager{
    @Autowired
    private ClassSessionRepository classSessionRepository;


    @Override
    public ClassSession addClassSession(ClassSession classSession) {
        if (!classSessionRepository.existsById(classSession.getId())) {
            return classSessionRepository.save(classSession);
        } else {
            System.out.println("the class session already exist");
            return null;
        }
    }

    @Override
    public boolean deleteClassSession(ClassSession classSession) {
        if (classSessionRepository.existsById(classSession.getId())) {
            classSessionRepository.deleteById(classSession.getId());
            return true;
        } else {
            System.out.println("the class session you are looking for doesnt exist");
            return false;
        }
    }

    @Override
    public List<ClassSession> getAllClassSessions() {
        return classSessionRepository.findAll();
    }

    @Override
    public ClassSession getClassSessionById(Integer id){ return classSessionRepository.findById(id).orElse(null);}

    @Override
    public  ClassSession updateClassSession(ClassSession classSession){ return classSessionRepository.save(classSession);}
    @Override
    public List<ClassSession> getClassSessionsByCourseId(Integer courseId) {
        return classSessionRepository.findByCourseId(courseId);
    }

}
