package ma.xproce.studentsmanaging.web;

import ma.xproce.studentsmanaging.dao.entities.ClassSession;
import ma.xproce.studentsmanaging.dao.entities.Course;
import ma.xproce.studentsmanaging.dao.entities.Professor;
import ma.xproce.studentsmanaging.service.ClassSessionManager;
import ma.xproce.studentsmanaging.service.CourseManager;
import ma.xproce.studentsmanaging.service.ProfessorManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class CourseControler {
    @Autowired
    private CourseManager courseManager;
    @Autowired
    private ClassSessionManager classSessionManager;
    @Autowired
    private ProfessorManager professorManager;
    @GetMapping("/getCoursesList")
    public String getAllCourses(Model model, @RequestParam(name = "page",defaultValue = "0") int page,
                                   @RequestParam(name = "taille" ,defaultValue = "5") int taille) {
        Page<Course> courses = courseManager.getAllCourses(page,taille);
        model.addAttribute("listCourses", courses.getContent());
        int[] pages = new int[courses.getTotalPages()];
        model.addAttribute("pages" , pages);
        model.addAttribute("currentPage", page);

        return "courses";
    }
    @GetMapping("/updateCourse/{id}")
    public String showUpdateForm(@PathVariable Integer id, Model model) {
        Course course = courseManager.getCourseById(id);
        model.addAttribute("course", course);
        return "updateCourse";
    }
    @PostMapping("/updateCourse")
    public String updateCourse(@RequestParam(name = "courseId") Integer id,
                               @RequestParam(name = "courseName") String name,
                               @RequestParam(name = "date") LocalDate date ,
                               @RequestParam(name = "classSessionId") Integer classSessionId ,
                               @RequestParam(name = "datestart") double datestart,
                               @RequestParam(name = "dateend") double dateend
                               ) {
        Course course = courseManager.getCourseById(id);
        ClassSession classSession = classSessionManager.getClassSessionById(classSessionId);
        if (course != null) {
            course.setName(name);
            classSession.setStart(datestart);
            classSession.setEnd(dateend);
            classSession.setDate(date);
            classSessionManager.updateClassSession(classSession);
            courseManager.updateCourse(course);
        }
        return "redirect:/getCoursesList";
    }
    @GetMapping("/addCourse")
    public String showAddCourseForm(Model model) {
        return "addCourse";
    }
    @PostMapping("/addCourse")
    public String addProfessor(@RequestParam("courseName") String courseName,
                               @RequestParam("courseStart") double start,
                               @RequestParam("courseEnd") double End,
                               @RequestParam("courseDate") LocalDate date,
                               @RequestParam("courseProfessorf") String professorf,
                               @RequestParam("courseProfessorl") String professorl) {

        Course course = new Course();
        course.setName(courseName);
        courseManager.addCourse(course);
        Professor professor1 = new Professor();
        professor1.setFname(professorf);
        professor1.setLname(professorl);
        professorManager.addProfessor(professor1);
        course.setProfessor(professor1);
        courseManager.updateCourse(course);
        ClassSession classSession = new ClassSession();
        classSession.setCourse(course);
        classSession.setStart(start);
        classSession.setEnd(End);
        classSession.setDate(date);
        classSession.setCourse(course);
        classSessionManager.addClassSession(classSession);
        return "redirect:/getCoursesList";
    }

    @GetMapping("/deleteCourse/{id}")
    public String deleteCourse(Model model, @PathVariable Integer id) {

        Course course = courseManager.getCourseById(id);
        Professor professor = course.getProfessor();

        professor.setCourse(null);
        course.setProfessor(null);
        courseManager.updateCourse(course);
        professorManager.deleteProfessor(professor);
        //courseManager.deleteCourse(course);




        //Integer csCourseID = course.getId();

        return "redirect:/getCoursesList";
    }


}
