package uz.nt.itcenter.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.bind.DefaultValue;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(generator = "studentIdSeq")
    @SequenceGenerator(name = "studentIdSeq", sequenceName = "student_id_seq", allocationSize = 1)
    private Integer id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String phoneNumber;
    private String username;
    private String password;
    private String gender;
    private String image;
//    private Group group;
    private Boolean isActive=true;
}
