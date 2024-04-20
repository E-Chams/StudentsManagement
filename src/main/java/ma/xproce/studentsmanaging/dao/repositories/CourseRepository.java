package ma.xproce.studentsmanaging.dao.repositories;

import ma.xproce.studentsmanaging.dao.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CourseRepository extends JpaRepository<Course,Integer> {
}
