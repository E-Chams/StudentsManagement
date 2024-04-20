package ma.xproce.studentsmanaging.web;

import ma.xproce.studentsmanaging.dao.entities.ClassSession;
import ma.xproce.studentsmanaging.dao.entities.Course;
import ma.xproce.studentsmanaging.dao.entities.Professor;
import ma.xproce.studentsmanaging.dao.entities.Student;
import ma.xproce.studentsmanaging.service.CourseManager;
import ma.xproce.studentsmanaging.service.ProfessorManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProfessorController {
    @Autowired
    private ProfessorManager professorManager;
    @Autowired
    private CourseManager courseManager;

    @GetMapping("/getProfessorsList")
    public String getAllProfessors(Model model, @RequestParam(name = "page",defaultValue = "0") int page,
                                 @RequestParam(name = "taille" ,defaultValue = "4") int taille) {
        Page<Professor> professors = professorManager.getAllProfessors(page,taille);
        model.addAttribute("listProfessors", professors.getContent());
        int[] pages = new int[professors.getTotalPages()];
        model.addAttribute("pages" , pages);
        model.addAttribute("currentPage", page);

        return "professors";
    }

    @GetMapping("/addProfessor")
    public String showAddProfessorForm(Model model) {
        List<Course> courses = courseManager.getAllCourses();
        model.addAttribute("courses", courses);
        return "addProfessor";
    }

    @PostMapping("/addProfessor")
    public String addStudent(@RequestParam("fname") String firstName,
                             @RequestParam("lname") String lastName,
                             @RequestParam("course") String coursename) {


        Professor professor = new Professor();
        professor.setFname(firstName);
        professor.setLname(lastName);

        Course course = new Course();
        course.setName(coursename);
        courseManager.addCourse(course);
        professor.setCourse(course);
        professorManager.addProfessor(professor);

        return "redirect:/getProfessorsList";
    }

}
