package ma.xproce.studentsmanaging.dao.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@Table(name = "Users")
@AllArgsConstructor
@NoArgsConstructor
public class UserM {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int id;
    @Column(unique = true)
    private  String username;
    private String password;
    private String fname;
    private String lname;
    private Date createdAt;
    private  Date updatedAt;
    @Enumerated(EnumType.ORDINAL)
    private Role role;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String imgP;


}
