package uz.nt.itcenter.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Assistant {
    @Id
    @GeneratedValue(generator = "assistantIdSeq")
    @SequenceGenerator(name = "assistantIdSeq", sequenceName = "assistant_id_seq", allocationSize = 1)
    private Integer id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String phoneNumber;
    private String direction;
    private String username;
    private String password;
    private String gender;
    private String image;
    //    private Group group;
    private Boolean isActive;
}
