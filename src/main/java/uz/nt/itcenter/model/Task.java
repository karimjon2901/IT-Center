package uz.nt.itcenter.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(generator = "taskIdSeq")
    @SequenceGenerator(name = "taskIdSeq", sequenceName = "task_id_seq", allocationSize = 1)
    private Integer id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private Date date;
    @ManyToOne
    private Groups groups;
    private Boolean isActive=true;
}
