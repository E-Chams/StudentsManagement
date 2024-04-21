package ma.xproce.studentsmanaging.service;

import ma.xproce.studentsmanaging.dao.entities.ClassSession;
import ma.xproce.studentsmanaging.dao.entities.Course;

import java.util.List;

public interface ClassSessionManager {
    public ClassSession addClassSession(ClassSession classSession);

    public boolean deleteClassSession(ClassSession classSession);

    List<ClassSession> getAllClassSessions();
    public  ClassSession getClassSessionById(Integer id);
    public  ClassSession updateClassSession(ClassSession classSession);
}
