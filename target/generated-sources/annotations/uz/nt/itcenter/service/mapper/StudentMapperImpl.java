package uz.nt.itcenter.service.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import uz.nt.itcenter.dto.StudentDto;
import uz.nt.itcenter.model.Student;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-02T07:06:18+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.7 (Amazon.com Inc.)"
)
@Component
public class StudentMapperImpl extends StudentMapper {

    @Override
    public StudentDto toDto(Student student) {
        if ( student == null ) {
            return null;
        }

        StudentDto studentDto = new StudentDto();

        studentDto.setId( student.getId() );
        studentDto.setFirstName( student.getFirstName() );
        studentDto.setLastName( student.getLastName() );
        studentDto.setAge( student.getAge() );
        studentDto.setPhoneNumber( student.getPhoneNumber() );
        studentDto.setUsername( student.getUsername() );
        studentDto.setPassword( student.getPassword() );
        studentDto.setGender( student.getGender() );

        studentDto.setImageUrl( student.getImage() );
        studentDto.setImage( null );

        return studentDto;
    }

    @Override
    public Student toEntity(StudentDto studentDto) {
        if ( studentDto == null ) {
            return null;
        }

        Student student = new Student();

        student.setId( studentDto.getId() );
        student.setFirstName( studentDto.getFirstName() );
        student.setLastName( studentDto.getLastName() );
        student.setAge( studentDto.getAge() );
        student.setPhoneNumber( studentDto.getPhoneNumber() );
        student.setUsername( studentDto.getUsername() );
        student.setPassword( studentDto.getPassword() );
        student.setGender( studentDto.getGender() );

        student.setImage( imageService.saveFile(studentDto.getImage()) );

        return student;
    }
}
