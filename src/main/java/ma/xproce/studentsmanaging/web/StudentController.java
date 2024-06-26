package ma.xproce.studentsmanaging.web;

import ma.xproce.studentsmanaging.dao.entities.ClassSession;
import ma.xproce.studentsmanaging.dao.entities.Course;
import ma.xproce.studentsmanaging.dao.entities.Student;
import ma.xproce.studentsmanaging.dao.entities.UserM;
import ma.xproce.studentsmanaging.dao.repositories.ClassSessionRepository;
import ma.xproce.studentsmanaging.service.ClassSessionManager;
import ma.xproce.studentsmanaging.service.CourseManager;
import ma.xproce.studentsmanaging.service.StudentManager;
import ma.xproce.studentsmanaging.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Controller
public class StudentController {
    @Autowired
    private StudentManager studentManager;
    @Autowired
    private CourseManager courseManager;
    @Autowired
    private ClassSessionManager classSessionManager;
    @Autowired
    private ClassSessionRepository classSessionRepository;
    @Autowired
    private UserManager userManager;

    @GetMapping("/getStudentsList")
    public String getAllStudents(Model model,
                                 @RequestParam(name = "page", defaultValue = "0") int page,
                                 @RequestParam(name = "taille", defaultValue = "6") int taille,
                                 @RequestParam(name = "keyword", defaultValue = "") String keyword) {

        Page<Student> students;

        if (!keyword.isEmpty()) {
            students = studentManager.searchStudent(keyword,page,taille);
        } else {
            students = studentManager.getAllStudents(page, taille);
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserM user = userManager.findByLogin(username);
        Integer userId = user.getId();
        String userImg = user.getImgP();

        model.addAttribute("username", username);
        model.addAttribute("userImg", userImg);

        model.addAttribute("listStudent", students.getContent());
        int[] pages = new int[students.getTotalPages()];
        model.addAttribute("pages", pages);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);

        List<Student> latestStudents = studentManager.getLastThreeStudents();
        model.addAttribute("latestStudents", latestStudents);

        return "listStudents";
    }




    @GetMapping("/deleteStudent/{id}")
    public String deleteStudent(Model model , @PathVariable Integer id){
        Student student = studentManager.getStudentById(id);

        for (ClassSession classSession : student.getClassSessions()) {
            classSession.getStudents().remove(student);
        }
        studentManager.deleteStudent(student.getId());
        return "redirect:/getStudentsList";
    }



    @GetMapping("/updateStudent/{id}")
    public String showUpdateForm(@PathVariable Integer id, Model model) {
        Student student = studentManager.getStudentById(id);
        model.addAttribute("student", student);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserM user = userManager.findByLogin(username);
        Integer userId = user.getId();
        String userImg = user.getImgP();

        model.addAttribute("username", username);
        model.addAttribute("userImg", userImg);
        return "updateStudent";
    }

    @PostMapping("/updateStudent")
    public String updateStudent(@RequestParam("file") MultipartFile file,
                                @RequestParam(name = "studentId") Integer id,
                                @RequestParam String fname, @RequestParam String lname) {
        Student student = studentManager.getStudentById(id);
        if (student != null) {
            student.setFname(fname);
            student.setLname(lname);
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            if (fileName.contains("..")) {
                System.out.println("not a a valid file");
            }
            try {
                student.setImgP(Base64.getEncoder().encodeToString(file.getBytes()));
            } catch (IOException e) {
                e.printStackTrace();

            }
            studentManager.updateStudent(student);
        }
        return "redirect:/getStudentsList";
    }

    @GetMapping("/deleteCourse/{studentId}/{courseId}")
    public String deleteCourse(@PathVariable("studentId") int studentId,
                               @PathVariable("courseId") int courseId,
                               Model model) {
        System.out.println(courseId);
        Student student = studentManager.getStudentById(studentId);
        Course course = courseManager.getCourseById(courseId);
        ClassSession classSession;

        // Remove the course from the student's list of courses

        //student.getClassSessions().removeIf();
        for ( ClassSession classSessions : student.getClassSessions()){
            if( classSessions.getCourse().getId().equals(courseId) )
                classSessions.getStudents().remove(student);
            classSessionRepository.save(classSessions);
        }

        student.getClassSessions().removeIf(classSession1 -> classSession1.getCourse().getId().equals(courseId));

        studentManager.updateStudent(student);

        // Update the model with the updated student
        model.addAttribute("student", student);

         return "redirect:/updateStudent/{studentId}";
    }

    @GetMapping("/addStudent")
    public String showAddStudentForm(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserM user = userManager.findByLogin(username);
        Integer userId = user.getId();
        String userImg = user.getImgP();

        model.addAttribute("username", username);
        model.addAttribute("userImg", userImg);
        List<Course> courses = courseManager.getAllCourses();
        model.addAttribute("courses", courses);
        return "addStudent";
    }

    @PostMapping("/addStudent")
    public String addStudent(@RequestParam("file") MultipartFile file
                            , @RequestParam("fname") String firstName,
                             @RequestParam("lname") String lastName,
                             @RequestParam("courses") List<Integer> courseIds) {


        Student student = new Student();
        student.setFname(firstName);
        student.setLname(lastName);
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if (fileName.contains("..")) {
            System.out.println("not a a valid file");
        }
        try {
            student.setImgP(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();

        }

        List<Course> selectedCourses = courseManager.getCoursesByIds(courseIds);
        //System.out.println(courseIds);

        List<ClassSession> classSessions = new ArrayList<>();

        for (Course course : selectedCourses) {
            ClassSession classSession = classSessionManager.getClassSessionById(course.getId());
           // classSession.setCourse(course);
            classSession.getStudents().add(student);
            classSessions.add(classSession);
        }
        student.setClassSessions(classSessions);

        studentManager.addStudent(student);

        return "redirect:/getStudentsList?success";
    }


}
