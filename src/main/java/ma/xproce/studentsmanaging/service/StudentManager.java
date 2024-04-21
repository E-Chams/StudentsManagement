package ma.xproce.studentsmanaging.service;

import ma.xproce.studentsmanaging.dao.entities.Course;
import ma.xproce.studentsmanaging.dao.entities.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentManager {

    public Student addStudent(Student student);

    public void deleteStudent(int id);

    public Page<Student> getAllStudents(int page , int taille);
    public Student getStudentById(int id);

    public Student updateStudent(Student student);
    public List<Course> getCoursesForStudent(int id);
    Page<Student> searchStudent(String keyword, int page , int taille);


}
