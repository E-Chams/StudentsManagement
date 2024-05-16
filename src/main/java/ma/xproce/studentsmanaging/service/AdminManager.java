package ma.xproce.studentsmanaging.service;

import ma.xproce.studentsmanaging.dao.entities.Admin;

public interface AdminManager {
    public Admin findByUsername(String username);
    public void saveAdmin(Admin admin);

}
