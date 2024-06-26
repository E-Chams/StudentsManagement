package ma.xproce.studentsmanaging.service;

import ma.xproce.studentsmanaging.dao.entities.UserM;
import ma.xproce.studentsmanaging.dao.entities.Role;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import ma.xproce.studentsmanaging.dao.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;

    //private static List<UserM> users = new ArrayList<>();

    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void postConstruct() {
        if (userRepository.count() == 0) {  // Check if the admin user already exists
            UserM user = new UserM();
            user.setRole(Role.ADMIN);
            user.setUsername("admin");
            user.setPassword(passwordEncoder.encode("abc"));
            userRepository.save(user);
        }
    }

    public int register(UserM user) {
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user.getId();
    }

    public UserM findByLogin(String login) {
        return userRepository.findByUsername(login).get();
        /*return users.stream().filter(user -> user.getUsername().equals(login))
                .findFirst()
                .orElse(null);*/
    }


}