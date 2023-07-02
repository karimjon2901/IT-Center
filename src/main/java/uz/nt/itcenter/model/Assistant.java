package uz.nt.itcenter.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
    @OneToMany
    private List<Groups> groups;
    private Boolean isActive;
}
