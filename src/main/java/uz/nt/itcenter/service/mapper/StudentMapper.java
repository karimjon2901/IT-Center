package uz.nt.itcenter.service.mapper;

import org.mapstruct.Mapper;
import uz.nt.itcenter.dto.StudentDto;
import uz.nt.itcenter.model.Student;
@Mapper(componentModel = "spring")
public abstract class StudentMapper implements CommonMapper<StudentDto, Student>{
    @Override
    public abstract StudentDto toDto(Student student);

    @Override
    public abstract Student toEntity(StudentDto studentDto);
}
