package ma.xproce.studentsmanaging.service;

import ma.xproce.studentsmanaging.dao.entities.Student;
import ma.xproce.studentsmanaging.dao.entities.UserM;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserManager {
    public List<UserM> getAllUsers();

    public UserM getUserById(int id);
    public  UserM findByLogin(String username);
}
