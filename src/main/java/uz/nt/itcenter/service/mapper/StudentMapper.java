package uz.nt.itcenter.service.mapper;

import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import uz.nt.itcenter.dto.StudentDto;
import uz.nt.itcenter.model.Student;
import uz.nt.itcenter.service.ImageService;

@Mapper(componentModel = "spring")
@RequiredArgsConstructor
public abstract class StudentMapper implements CommonMapper<StudentDto, Student>{
    ImageService imageService;
    @Override
    @Mapping(target = "imageUrl", expression = "java(student.getImage())")
    @Mapping(target = "image", expression = "java(null)")
    public abstract StudentDto toDto(Student student);

    @Override
    @Mapping(target = "image", expression = "java(imageService.saveFile(studentDto.getImage()))")
    public abstract Student toEntity(StudentDto studentDto);
}
