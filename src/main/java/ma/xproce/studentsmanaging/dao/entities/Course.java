package ma.xproce.studentsmanaging.dao.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name="Courses")
@NoArgsConstructor
@AllArgsConstructor

public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Date createdAt;
    private  Date updatedAt;

    @OneToOne
    private Professor professor;

    @OneToMany(mappedBy = "course" , cascade = CascadeType.MERGE)
    private List<ClassSession> classSessions;

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}