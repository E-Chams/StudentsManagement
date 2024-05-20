package ma.xproce.studentsmanaging.web;
import jakarta.servlet.http.HttpServletRequest;
import ma.xproce.studentsmanaging.dao.entities.UserM;
import ma.xproce.studentsmanaging.dao.repositories.UserRepository;
import ma.xproce.studentsmanaging.service.UserManager;
import ma.xproce.studentsmanaging.service.UserManagerService;
import ma.xproce.studentsmanaging.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;

@Controller
@RequiredArgsConstructor

public class UserController {
    private final PasswordEncoder passwordEncoder;
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


    @GetMapping("/settings")
    public String editSettings(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserM user = userManager.findByLogin(username);
        Integer userId = user.getId();
        String userImg = user.getImgP();
        model.addAttribute("username", username);
        model.addAttribute("userImg", userImg);
        model.addAttribute("userId",userId);
        model.addAttribute("usr",user);
        return "settings";
    }

    @PostMapping("/edit")
    public String updateSettings(Model model,
                                 @ModelAttribute UserM user, RedirectAttributes redirectAttributes,
                                 @RequestParam String lname,
                                 @RequestParam String fname,
                                 @RequestParam String email,
                                 @RequestParam("file") MultipartFile file) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String usrname = authentication.getName();
        UserM userr = userManager.findByLogin(usrname);
        Integer userId = userr.getId();
        String userImg = userr.getImgP();
        userr.setEmail(email);
        userr.setFname(fname);
        userr.setLname(lname);
        userr.setUpdatedAt(new Date());
        userRepository.save(userr);
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if (fileName.contains("..")) {
            System.out.println("not a a valid file");
        }
        try {
            userr.setImgP(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();

        }






        model.addAttribute("username", usrname);
        model.addAttribute("userImg", userImg);
        redirectAttributes.addFlashAttribute("successMessage", "Settings updated successfully.");
        return "redirect:/settings";
    }
@GetMapping("profile")
public String showProfile(Model model) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = authentication.getName();
    UserM user = userManager.findByLogin(username);
    Integer userId = user.getId();
    String userImg = user.getImgP();
    model.addAttribute("username", username);
    model.addAttribute("userImg", userImg);
    model.addAttribute("userId",userId);
    model.addAttribute("usr",user);
    return "profile";
}


}