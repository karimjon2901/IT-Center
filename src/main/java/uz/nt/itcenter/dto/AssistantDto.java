package uz.nt.itcenter.dto;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import uz.nt.itcenter.model.Groups;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssistantDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String phoneNumber;
    private String direction;
    private String username;
    private String password;
    private String gender;
    private MultipartFile image;
    private String imageUrl;
    private List<GroupDto> group;
}
