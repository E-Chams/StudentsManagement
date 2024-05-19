package ma.xproce.studentsmanaging.dao.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@Table(name="Professors")
@NoArgsConstructor
@AllArgsConstructor

public class Professor {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String fname;
    private String lname;
    private String  phone;
    private Date createdAt;
    private  Date updatedAt;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String imgP;


    @OneToOne(mappedBy = "professor", cascade = CascadeType.MERGE, orphanRemoval = true)
    private Course course;

    @Override
    public String toString() {
        return "Professor{" +
                "id=" + id +
                ", fname='" + fname+ '\'' +
                ", lname='" + lname+ '\'' +
                '}';
    }

}
