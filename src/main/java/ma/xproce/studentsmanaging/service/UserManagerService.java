package ma.xproce.studentsmanaging.service;

import ma.xproce.studentsmanaging.dao.entities.UserM;
import ma.xproce.studentsmanaging.dao.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserManagerService implements UserManager{

    @Autowired
    private UserRepository userRepository;
    @Override
    public List <UserM> getAllUsers(){return userRepository.findAll();};
    @Override
    public UserM getUserById(int id){return userRepository.findById(id).orElse(null);};
    @Override
    public UserM findByLogin(String login){return userRepository.findByUsername(login).orElse(null);}
}
