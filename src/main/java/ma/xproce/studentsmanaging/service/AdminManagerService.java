package ma.xproce.studentsmanaging.service;

import ma.xproce.studentsmanaging.dao.entities.Admin;
import ma.xproce.studentsmanaging.dao.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminManagerService implements AdminManager {

    @Autowired
    private AdminRepository adminRepository;
    @Override
    public Admin findByUsername(String username) {
        return adminRepository.findByUsername(username).orElse(null);
    }
    @Override
    public void saveAdmin(Admin admin) {
        adminRepository.save(admin);
    }


}
