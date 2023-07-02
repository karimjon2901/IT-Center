package uz.nt.itcenter.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.nt.itcenter.model.Groups;

import java.time.LocalDateTime;
import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private Integer id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private Date date;
    private Groups groups;
}
