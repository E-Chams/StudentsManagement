package ma.xproce.studentsmanaging.web;
import jakarta.servlet.http.HttpServletRequest;
import ma.xproce.studentsmanaging.dao.entities.UserM;
import ma.xproce.studentsmanaging.dao.repositories.UserRepository;
import ma.xproce.studentsmanaging.service.UserManager;
import ma.xproce.studentsmanaging.service.UserManagerService;
import ma.xproce.studentsmanaging.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Controller
@RequiredArgsConstructor

public class UserController {
     @Autowired
     private UserRepository userRepository;

     @Autowired
     private  UserManager userManager;

    private final UserService userService;

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        UserM userM=new UserM();
        model.addAttribute("user",userM);
        return "login";
    }


    @GetMapping("/registration")
    public String getRegistrationPage(Model model) {
        model.addAttribute("user", new UserM());
        return "register";
    }

    @PostMapping("/registration")
    public String registerUser(@ModelAttribute UserM user, @RequestParam("file") MultipartFile file,
                               Model model,
                               @RequestParam("userId") String userId) {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if (fileName.contains("..")) {
            System.out.println("not a a valid file");
        }
        try {
            user.setImgP(Base64.getEncoder().encodeToString(file.getBytes()));
            userService.register(user);
            model.addAttribute("userId", userId);
        } catch (IOException e) {
            e.printStackTrace();

        }
        return "redirect:/login?success";
      }




}