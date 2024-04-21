package ma.xproce.studentsmanaging.dao.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name="Students")
@NoArgsConstructor
@AllArgsConstructor

public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fname;
    private String lname;
    private Date createdAt;
    private  Date updatedAt;


    @ManyToMany(mappedBy = "students", fetch = FetchType.EAGER ,cascade = CascadeType.MERGE)
    private List<ClassSession> classSessions;


}
