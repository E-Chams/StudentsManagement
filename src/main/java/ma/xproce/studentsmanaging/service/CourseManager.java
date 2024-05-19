package ma.xproce.studentsmanaging.service;

import ma.xproce.studentsmanaging.dao.entities.Course;
import ma.xproce.studentsmanaging.dao.entities.Professor;
import ma.xproce.studentsmanaging.dao.entities.Student;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CourseManager {
    public Course addCourse(Course course);

    public boolean deleteCourse(Course course);

    public Page<Course> getAllCourses(int page , int taille);
    public List<Course> getAllCourses();
    public Course getCourseById(Integer id);
    public Course getCourseByName(String name);
    public List<Course> getCoursesByIds(List<Integer> courseIds);
    public Course updateCourse(Course course);
    public Page<Course> searchCourse(String keyword, int page , int taille);

    public Integer getCoursesCount();
}
