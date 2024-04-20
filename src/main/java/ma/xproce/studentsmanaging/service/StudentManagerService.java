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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        return studentRepository.findAll(PageRequest.of(page,taille));
    }

    @Override
    public Student getStudentById(int id){
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public Student updateStudent(Student student) {
        return studentRepository.save(student);
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
}
