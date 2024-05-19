package ma.xproce.studentsmanaging.web;

import ma.xproce.studentsmanaging.dao.entities.*;
import ma.xproce.studentsmanaging.service.ClassSessionManager;
import ma.xproce.studentsmanaging.service.CourseManager;
import ma.xproce.studentsmanaging.service.ProfessorManager;
import ma.xproce.studentsmanaging.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Controller
public class ProfessorController {
    @Autowired
    private ProfessorManager professorManager;
    @Autowired
    private CourseManager courseManager;
    @Autowired
    private ClassSessionManager classSessionManager;
    @Autowired
    private UserManager userManager;
    @GetMapping("/getProfessorsList")
    public String getAllProfessors(Model model, @RequestParam(name = "page",defaultValue = "0") int page,
                                 @RequestParam(name = "taille" ,defaultValue = "4") int taille,
                                   @RequestParam(name = "keyword", defaultValue = "") String keyword) {
        Page<Professor> professors;
        if (!keyword.isEmpty()) {
            professors = professorManager.searchProfessor(keyword,page,taille);
        } else {
            professors = professorManager.getAllProfessors(page, taille);
        }
        model.addAttribute("listProfessors", professors.getContent());
        int[] pages = new int[professors.getTotalPages()];
        model.addAttribute("pages" , pages);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);

        return "professors";
    }

    @GetMapping("/addProfessor")
    public String showAddProfessorForm(Model model) {
        List<Course> courses = courseManager.getAllCourses();
        model.addAttribute("courses", courses);
        return "addProfessor";
    }

    @PostMapping("/addProfessor")
    public String addProfessor(@RequestParam("file") MultipartFile file, @RequestParam("fname") String firstName,
                               @RequestParam("lname") String lastName,
                               @RequestParam("course") String coursename) {

        Professor professor = new Professor();
        professor.setFname(firstName);
        professor.setLname(lastName);
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if (fileName.contains("..")) {
            System.out.println("not a a valid file");
        }
        try {
            professor.setImgP(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();}
        professorManager.addProfessor(professor);

        Course course = new Course();
        course.setName(coursename);
        course.setProfessor(professor);
        courseManager.addCourse(course);


        professor.setCourse(course);
        professorManager.updateProfessor(professor);

        ClassSession classSession = new ClassSession();
        classSession.setCourse(course);
        classSessionManager.addClassSession(classSession);


        return "redirect:/getProfessorsList";
    }
    @GetMapping("/deleteProfessor/{id}")
    public String deleteProfessor(Model model, @PathVariable Integer id) {

            Professor professor = professorManager.getProfessorById(id);
            Course course = professor.getCourse();
            course.setProfessor(null);
            courseManager.updateCourse(course);
            professor.setCourse(null);
            professorManager.updateProfessor(professor);


            courseManager.deleteCourse(course);
            professorManager.deleteProfessor(professor);

            return "redirect:/getProfessorsList";


    }






    @GetMapping("/updateProfessor/{id}")
    public String showUpdateForm(@PathVariable Integer id, Model model) {
        Professor professor = professorManager.getProfessorById(id);
        model.addAttribute("professor", professor);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserM user = userManager.findByLogin(username);
        Integer userId = user.getId();
        String userImg = user.getImgP();

        model.addAttribute("username", username);
        model.addAttribute("userImg", userImg);
        return "updateProfessor";
    }
    @PostMapping("/updateProfessor")
    public String updateProfessor(@RequestParam("file") MultipartFile file,
                                  @RequestParam(name = "professorId") Integer id,
                                  @RequestParam String fname, @RequestParam String lname ,
                                  @RequestParam(name = "courseId") Integer courseId,
                                  @RequestParam(name = "course") String course) {


        Professor professor = professorManager.getProfessorById(id);
        Course course1 = courseManager.getCourseById(courseId);
        if (professor != null) {
            professor.setLname(lname);
            professor.setFname(fname);
            course1.setName(course);
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            if (fileName.contains("..")) {
                System.out.println("not a a valid file");
            }
            try {
                professor.setImgP(Base64.getEncoder().encodeToString(file.getBytes()));
            } catch (IOException e) {
                e.printStackTrace();

            }
            courseManager.updateCourse(course1);
            professorManager.updateProfessor(professor);
        }
        return "redirect:/getProfessorsList";
    }



}
