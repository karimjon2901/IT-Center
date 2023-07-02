package uz.nt.itcenter.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import uz.nt.itcenter.model.Assistant;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupDto {
    private Integer id;
    private String direction;
    private Integer group_number;
    private String teacher;
    private Assistant assistant;
    private List<DayOfWeek> day;
    private Time time;
    private Timestamp createdAt;
    private MultipartFile image;
    private String imageUrl;
    private String classroom_name;
}
