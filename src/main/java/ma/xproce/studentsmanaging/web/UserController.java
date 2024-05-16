package ma.xproce.studentsmanaging.web;
import ma.xproce.studentsmanaging.dao.entities.UserM;
import ma.xproce.studentsmanaging.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor

public class UserController {


    private final UserService userService;

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/registration")
    public String getRegistrationPage(Model model) {
        model.addAttribute("user", new UserM());
        return "register";
    }

    @PostMapping("/registration")
    public String registerUser(@ModelAttribute UserM user) {
        userService.register(user);
        return "redirect:/login?success";
    }



}
