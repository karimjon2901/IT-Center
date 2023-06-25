package uz.nt.itcenter.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Group {
    @Id
    @GeneratedValue(generator = "groupIdSeq")
    @SequenceGenerator(name = "groupIdSeq", sequenceName = "group_id_seq", allocationSize = 1)
    private Integer id;
    private String direction;
    private Integer group_number;
    private String teacher;
    @ManyToOne
    private Assistant assistant;
    private List<DayOfWeek> day;
    private Time time;
    private Timestamp createdAt;
    private String image;
    private String classroom_name;
    private Boolean isActive;
}
