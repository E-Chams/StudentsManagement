package ma.xproce.studentsmanaging.web;

import ma.xproce.studentsmanaging.dao.entities.Course;
import ma.xproce.studentsmanaging.dao.entities.Professor;
import ma.xproce.studentsmanaging.service.CourseManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CourseControler {
    @Autowired
    private CourseManager courseManager;
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
}
