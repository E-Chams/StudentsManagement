package ma.xproce.studentsmanaging.service;

import jakarta.transaction.Transactional;
import ma.xproce.studentsmanaging.dao.entities.ClassSession;
import ma.xproce.studentsmanaging.dao.entities.Course;
import ma.xproce.studentsmanaging.dao.entities.Student;
import ma.xproce.studentsmanaging.dao.repositories.ClassSessionRepository;
import ma.xproce.studentsmanaging.dao.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StudentManagerService implements StudentManager{

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ClassSessionRepository classSessionRepository;
    @Override
    public Student addStudent(Student student) {
        if (!studentRepository.existsById(student.getId())) {
            student.setCreatedAt(new Date());
            return studentRepository.save(student);
        } else {
            System.out.println("the Student already exist");
            return null;
        }
    }

    @Transactional
    public void deleteStudent(int id) {

        if (studentRepository.existsById(id))
        {
            studentRepository.deleteById(id);

        } else
        {
            System.out.println("the student doesnt exist");

        }

    }



    @Override
    public Page<Student> getAllStudents(int page , int taille) {

        PageRequest pageable = PageRequest.of(page, taille);
        return studentRepository.findAll(pageable);
    }

    @Override
    public Student getStudentById(int id){
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public Student updateStudent(Student student) {
        if(studentRepository.existsById(student.getId())){
            student.setUpdatedAt(new Date());
            return studentRepository.save(student);
        }
        else {
            System.out.println("The student doesnt exist");
            return null;
        }

    }
    @Override
    public List<Course> getCoursesForStudent(int studentId) {
        Student student = studentRepository.findById(studentId).orElse(null);
        List<Course> courses = new ArrayList<>();
        if (student != null) {
            List<ClassSession> classSessions = student.getClassSessions();
            for (ClassSession classSession : classSessions) {
                courses.add(classSession.getCourse());
            }
        }
        return courses;
    }
    @Override
    public Page<Student> searchStudent(String keyword, int page , int taille) {

        PageRequest pageable = PageRequest.of(page, taille);
        return studentRepository.findByFnameContaining(keyword,pageable);
    }


}
