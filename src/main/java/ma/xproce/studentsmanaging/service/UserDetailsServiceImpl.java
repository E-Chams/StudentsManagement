package ma.xproce.studentsmanaging.service;
import ma.xproce.studentsmanaging.dao.entities.UserM;

import ma.xproce.studentsmanaging.dao.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserM byLogin = userRepository.findByUsername(username).orElse(null);
        if (byLogin == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        return buildUserDetails(byLogin);
    }

    private UserDetails buildUserDetails(UserM user) {
        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();
    }


}