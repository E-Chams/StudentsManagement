package ma.xproce.studentsmanaging;

import ma.xproce.studentsmanaging.dao.entities.ClassSession;
import ma.xproce.studentsmanaging.dao.entities.Course;
import ma.xproce.studentsmanaging.dao.entities.Professor;
import ma.xproce.studentsmanaging.dao.entities.Student;
import ma.xproce.studentsmanaging.dao.repositories.ClassSessionRepository;
import ma.xproce.studentsmanaging.dao.repositories.CourseRepository;
import ma.xproce.studentsmanaging.dao.repositories.ProfessorRepository;
import ma.xproce.studentsmanaging.dao.repositories.StudentRepository;
import ma.xproce.studentsmanaging.service.ClassSessionManager;
import ma.xproce.studentsmanaging.service.CourseManager;
import ma.xproce.studentsmanaging.service.ProfessorManager;
import ma.xproce.studentsmanaging.service.StudentManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class StudentsManagingApplication {


    @Autowired
    private ClassSessionRepository classSessionRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private StudentRepository studentRepository;


    @Autowired
    private ClassSessionManager classSessionManager;
    @Autowired
    private CourseManager courseManager;
    @Autowired
    private ProfessorManager professorManager;
    @Autowired
    private StudentManager studentManager;

    public static void main(String[] args) {
        SpringApplication.run(StudentsManagingApplication.class, args);
    }



@Bean
    CommandLineRunner start(){
        return args -> {


//
//            //Entering Data for students
//            Student student1 = new Student();
//            student1.setFname("Salma");
//            student1.setLname("Echams");
//            studentRepository.save(student1);
//
//            Student student2 = new Student();
//            student2.setFname("Ibtissam");
//            student2.setLname("Daraaoui");
//            studentRepository.save(student2);
//
//            Student student3 = new Student();
//            student3.setFname("Inass");
//            student3.setLname("Mahloul");
//            studentRepository.save(student3);
//
//            Student student4 = new Student();
//            student4.setFname("Haytam");
//            student4.setLname("Maan");
//            studentRepository.save(student4);
//
//            Student student5 = new Student();
//            student5.setFname("Wail");
//            student5.setLname("Kodad");
//            studentRepository.save(student5);
//
//            Student student6 = new Student();
//            student6.setFname("Mohammed");
//            student6.setLname("Afkari");
//            studentRepository.save(student6);
//
//
//            Student student7 = new Student();
//            student7.setFname("Souhail");
//            student7.setLname("Mokadem");
//            studentRepository.save(student7);
//
//            Student student8 = new Student();
//            student8.setFname("kenza");
//            student8.setLname("Riahi");
//            studentRepository.save(student8);
//
//            Student student9 = new Student();
//            student9.setFname("Ismael");
//            student9.setLname("Zahdani");
//            studentRepository.save(student9);
//
//            //Entering data for courses
//            Course course1 = new Course();
//            course1.setName("JAVA");
//            courseRepository.save(course1);
//
//            Course course2 = new Course();
//            course2.setName("Python");
//            courseRepository.save(course2);
//
//            Course course3 = new Course();
//            course3.setName("C++");
//            courseRepository.save(course3);
//
//            Course course4 = new Course();
//            course4.setName("English");
//            courseRepository.save(course4);
//
//            Course course5 = new Course();
//            course5.setName("French");
//            courseRepository.save(course5);
//
//            Course course6 = new Course();
//            course6.setName("Arabic");
//            courseRepository.save(course6);
//
//            Course course7 = new Course();
//            course7.setName("Big data");
//            courseRepository.save(course7);
//
//            Course course8 = new Course();
//            course8.setName("Data warehouse");
//            courseRepository.save(course8);
//
//            //Entering Data for professors
//            Professor professor1 = new Professor();
//            professor1.setFname("Hajar");
//            professor1.setLname("Boughrib");
//            professor1.setCourse(course1);
//            professorRepository.save(professor1);
//
//            Professor professor2 = new Professor();
//            professor2.setFname("Ahmed");
//            professor2.setLname("Boudraa");
//            professor2.setCourse(course2);
//            professorRepository.save(professor2);
//
//            Professor professor3 = new Professor();
//            professor3.setFname("Fatima");
//            professor3.setLname("Ait slimane");
//            professor3.setCourse(course3);
//            professorRepository.save(professor3);
//
//            Professor professor4 = new Professor();
//            professor4.setFname("Karim");
//            professor4.setLname("Dahmani");
//            professor4.setCourse(course4);
//            professorRepository.save(professor4);
//
//            Professor professor5 = new Professor();
//            professor5.setFname("Mohammed");
//            professor5.setLname("Talbi");
//            professor5.setCourse(course5);
//            professorRepository.save(professor5);
//
//            Professor professor6 = new Professor();
//            professor6.setFname("Hassan");
//            professor6.setLname("Raqen");
//            professor6.setCourse(course6);
//            professorRepository.save(professor6);
//
//            Professor professor7 = new Professor();
//            professor7.setFname("Ibrahim");
//            professor7.setLname("Nouri");
//            professor7.setCourse(course7);
//            professorRepository.save(professor7);
//
//            Professor professor8 = new Professor();
//            professor8.setFname("Fatima");
//            professor8.setLname("Abrar");
//            professor8.setCourse(course8);
//            professorRepository.save(professor8);
//
//
//            //Course
//            course1.setProfessor(professor1);
//            courseRepository.save(course1);
//
//            course2.setProfessor(professor2);
//            courseRepository.save(course2);
//
//            course3.setProfessor(professor3);
//            courseRepository.save(course3);
//
//            course4.setProfessor(professor4);
//            courseRepository.save(course4);
//
//            course5.setProfessor(professor5);
//            courseRepository.save(course5);
//
//            course6.setProfessor(professor6);
//            courseRepository.save(course6);
//
//            course7.setProfessor(professor7);
//            courseRepository.save(course7);
//
//            course8.setProfessor(professor8);
//            courseRepository.save(course8);
//
//
//            //Entering data for Class sessions
//            ClassSession classSession1 = new ClassSession();
//            classSession1.setCourse(course1);
//            classSessionRepository.save(classSession1);
//
//            ClassSession classSession2 = new ClassSession();
//            classSession2.setCourse(course2);
//            classSessionRepository.save(classSession2);
//
//
//            ClassSession classSession3 = new ClassSession();
//            classSession3.setCourse(course3);
//            classSessionRepository.save(classSession3);
//
//            ClassSession classSession4 = new ClassSession();
//            classSession4.setCourse(course4);
//            classSessionRepository.save(classSession4);
//
//            ClassSession classSession5 = new ClassSession();
//            classSession5.setCourse(course5);
//            classSessionRepository.save(classSession5);
//
//            ClassSession classSession6 = new ClassSession();
//            classSession6.setCourse(course6);
//            classSessionRepository.save(classSession6);
//
//            ClassSession classSession7 = new ClassSession();
//            classSession7.setCourse(course7);
//            classSessionRepository.save(classSession7);
//
//            ClassSession classSession8 = new ClassSession();
//            classSession8.setCourse(course8);
//            classSessionRepository.save(classSession8);
//
//
//            //List of Students
//            List<Student> students = new ArrayList<>();
//            students.add(student1);
//            students.add(student2);
//            students.add(student3);
//            students.add(student4);
//            students.add(student5);
//            students.add(student6);
//            students.add(student7);
//            students.add(student8);
//            students.add(student9);
//
//            //List of class sessions
//            List<ClassSession> classSessions = new ArrayList<>();
//            classSessions.add(classSession1);
//            classSessions.add(classSession2);
//            classSessions.add(classSession3);
//            classSessions.add(classSession4);
//            classSessions.add(classSession5);
//            classSessions.add(classSession6);
//            classSessions.add(classSession7);
//            classSessions.add(classSession8);
//
//
//            //Completing data for Students
//            student1.setClassSessions(classSessions);
//            studentRepository.save(student1);
//
//            student2.setClassSessions(classSessions);
//            studentRepository.save(student2);
//
//            student3.setClassSessions(classSessions);
//            studentRepository.save(student3);
//
//            student4.setClassSessions(classSessions);
//            studentRepository.save(student4);
//
//            student5.setClassSessions(classSessions);
//            studentRepository.save(student5);
//
//            student6.setClassSessions(classSessions);
//            studentRepository.save(student6);
//
//            student7.setClassSessions(classSessions);
//            studentRepository.save(student7);
//
//            student8.setClassSessions(classSessions);
//            studentRepository.save(student8);
//
//            student9.setClassSessions(classSessions);
//            studentRepository.save(student9);
//
//
//
//            //Completing data for class Sessions
//            classSession1.setStudents(students);
//            classSession1.setDate(LocalDate.now());
//            classSession1.setStart(8);
//            classSession1.setEnd(9);
//            classSessionRepository.save(classSession1);
//
//            classSession2.setStudents(students);
//            classSession2.setDate(LocalDate.now());
//            classSession2.setStart(9);
//            classSession2.setEnd(10);
//            classSessionRepository.save(classSession2);
//
//            classSession3.setStudents(students);
//            classSession3.setDate(LocalDate.now());
//            classSession3.setStart(10);
//            classSession3.setEnd(11);
//            classSessionRepository.save(classSession3);
//
//            classSession4.setStudents(students);
//            classSession4.setDate(LocalDate.now());
//            classSession4.setStart(11);
//            classSession4.setEnd(12);
//            classSessionRepository.save(classSession4);
//
//            classSession5.setStudents(students);
//            classSession5.setDate(LocalDate.now());
//            classSession5.setStart(2);
//            classSession5.setEnd(3);
//            classSessionRepository.save(classSession5);
//
//            classSession6.setStudents(students);
//            classSession6.setDate(LocalDate.now());
//            classSession6.setStart(3);
//            classSession6.setEnd(4);
//            classSessionRepository.save(classSession6);
//
//            classSession7.setStudents(students);
//            classSession7.setDate(LocalDate.now());
//            classSession7.setStart(4);
//            classSession7.setEnd(5);
//            classSessionRepository.save(classSession7);
//
//            classSession8.setStudents(students);
//            classSession8.setDate(LocalDate.now());
//            classSession8.setStart(5);
//            classSession8.setEnd(6);
//            classSessionRepository.save(classSession8);
//
//
//
//
//
//            //Completing data for Courses
//            course1.setClassSessions(classSessions);
//            courseRepository.save(course1);
//
//            course2.setClassSessions(classSessions);
//            courseRepository.save(course2);
//
//            course3.setClassSessions(classSessions);
//            courseRepository.save(course3);
//
//            course4.setClassSessions(classSessions);
//            courseRepository.save(course4);
//
//            course5.setClassSessions(classSessions);
//            courseRepository.save(course5);
//
//            course6.setClassSessions(classSessions);
//            courseRepository.save(course6);
//
//            course7.setClassSessions(classSessions);
//            courseRepository.save(course7);
//
//            course8.setClassSessions(classSessions);
//            courseRepository.save(course8);

/*

            //  Testing the service part
            Student studentTEST = new Student();
            studentTEST.setFname("TEST");
            studentManager.addStudent(studentTEST);
            studentManager.deleteStudent(studentTEST);
            studentManager.getAllStudents();



*/















        };




}

}
