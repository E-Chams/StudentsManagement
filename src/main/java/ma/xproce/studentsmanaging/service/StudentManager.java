package ma.xproce.studentsmanaging.service;

import ma.xproce.studentsmanaging.dao.entities.Course;
import ma.xproce.studentsmanaging.dao.entities.Student;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentManager {

    public Student addStudent(Student student);

    public void deleteStudent(int id);

    public Page<Student> getAllStudents(int page , int taille);
    public Student getStudentById(int id);

    public Student updateStudent(Student student);
    public List<Course> getCoursesForStudent(int id);


}
