package ma.xproce.studentsmanaging.service;

import ma.xproce.studentsmanaging.dao.entities.Course;
import ma.xproce.studentsmanaging.dao.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CoursemanagerService implements CourseManager {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Course addCourse(Course course) {
        if ( !courseRepository.existsById(course.getId())) {
            return courseRepository.save(course);
        } else {
            System.out.println("the course already exist");
            return null;
        }
    }

    @Override
    public boolean deleteCourse(Course course) {
        if (courseRepository.existsById(course.getId())) {
            courseRepository.deleteById(course.getId());
            return true;
        } else {
            System.out.println("the course doesnt exist");
            return false;
        }
    }

    @Override
    public Page<Course> getAllCourses(int page, int taille) {
        return courseRepository.findAll(PageRequest.of(page, taille));
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(Integer id) {
        return courseRepository.findById(id).get();
    }


    @Override
    public List<Course> getCoursesByIds(List<Integer> courseIds) {
        List<Course> courses = new ArrayList<>();
        for (Integer id : courseIds) {
            Course course = this.getCourseById(id);
            if (course != null) {
                courses.add(course);
            }
        }
        return courses;
    }
}
