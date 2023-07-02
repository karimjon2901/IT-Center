package uz.nt.itcenter.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rating {
    @Id
    @GeneratedValue(generator = "ratingIdSeq")
    @SequenceGenerator(name = "ratingIdSeq", sequenceName = "rating_id_seq",allocationSize = 1)
    private Integer id;
    @ManyToOne
    private Student student;
    private String description;
    @ManyToOne
    private Task task;
    private LocalDateTime createdAt;
}
