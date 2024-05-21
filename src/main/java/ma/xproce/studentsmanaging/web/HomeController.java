package ma.xproce.studentsmanaging.web;

import ma.xproce.studentsmanaging.dao.entities.Student;
import ma.xproce.studentsmanaging.dao.entities.UserM;
import ma.xproce.studentsmanaging.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private ProfessorManager professorManager;
    @Autowired
    private CourseManager courseManager;
    @Autowired
    private ClassSessionManager classSessionManager;
    @Autowired
    private StudentManager studentManager;
    @Autowired
    private UserManager userManager;


    @GetMapping("/Home")
    public String index(Model model) {


        List<Student> students =studentManager.getallStudents();
        List<UserM> users = userManager.getAllUsers();
        model.addAttribute("Students",students);
        model.addAttribute("professorsCount", professorManager.getProfessorsCount());
        model.addAttribute("studentsCount", studentManager.getStudentsCount());
        model.addAttribute("coursesCount",courseManager.getCoursesCount());
        model.addAttribute("user",userManager.getAllUsers());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserM user = userManager.findByLogin(username);
        Integer userId = user.getId();
        String userImg = user.getImgP();

        model.addAttribute("username", username);
        model.addAttribute("userImg", userImg);

        List<Student> latestStudents = studentManager.getLastThreeStudents();
        model.addAttribute("latestStudents", latestStudents);



        return "Home";
        }

}
