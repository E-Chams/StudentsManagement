package ma.xproce.studentsmanaging.dao.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.LifecycleState;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "ClassSessions")
@AllArgsConstructor
@NoArgsConstructor
public class ClassSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate date;
    private double start;
    private double end;

    @ManyToOne()
    private Course course;

    @ManyToMany()
    private List<Student> students=new ArrayList<>();




}
