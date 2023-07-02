package uz.nt.itcenter.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import uz.nt.itcenter.dto.ResponseDto;
import uz.nt.itcenter.dto.StudentDto;
import uz.nt.itcenter.model.Student;
import uz.nt.itcenter.repository.StudentRepository;
import uz.nt.itcenter.service.ImageService;
import uz.nt.itcenter.service.StudentService;
import uz.nt.itcenter.service.mapper.StudentMapper;

import java.util.Optional;

import static uz.nt.itcenter.appStatus.AppStatusCodes.*;
import static uz.nt.itcenter.appStatus.AppStatusMessages.*;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final ImageService imageService;

    @Override
    public ResponseDto<StudentDto> add(StudentDto studentDto) {
        try{
            imageService.saveFile(studentDto.getImage());

            studentRepository.save(studentMapper.toEntity(studentDto));

            return ResponseDto.<StudentDto>builder()
                    .message(OK)
                    .code(OK_CODE)
                    .success(true)
                    .data(studentDto)
                    .build();
        } catch (Exception e){
            return ResponseDto.<StudentDto>builder()
                    .code(DATABASE_ERROR_CODE)
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<StudentDto> getById(Integer id) {
        if (id == null){
            return ResponseDto.<StudentDto>builder()
                    .message("ID is NULL!")
                    .code(VALIDATION_ERROR_CODE)
                    .build();
        }

        try{
            Optional<Student> byId = studentRepository.findById(id);

            if (byId.isEmpty()){
                return ResponseDto.<StudentDto>builder()
                        .code(NOT_FOUND_ERROR_CODE)
                        .message(NOT_FOUND)
                        .build();
            }

            return ResponseDto.<StudentDto>builder()
                    .message(OK)
                    .code(OK_CODE)
                    .success(true)
                    .data(studentMapper.toDto(byId.get()))
                    .build();
        } catch (Exception e){
            return ResponseDto.<StudentDto>builder()
                    .code(DATABASE_ERROR_CODE)
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<Page<StudentDto>> getAll(Integer size, Integer page) {
        Long count = studentRepository.count();

        PageRequest pageRequest = PageRequest.of(
                (count / size) <= page ?
                        (count % size == 0 ? (int) (count / size) - 1
                                : (int) (count / size))
                        : page,
                size
        );
        try{
            Page<StudentDto> all = studentRepository.findAll(pageRequest).map(studentMapper::toDto);
            return ResponseDto.<Page<StudentDto>>builder()
                    .message(OK)
                    .code(OK_CODE)
                    .success(true)
                    .data(all)
                    .build();
        } catch (Exception e){
            return ResponseDto.<Page<StudentDto>>builder()
                    .code(DATABASE_ERROR_CODE)
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<StudentDto> update(StudentDto studentDto) {
        if (studentDto.getId() == null){
            return ResponseDto.<StudentDto>builder()
                    .message("ID is NULL!")
                    .code(VALIDATION_ERROR_CODE)
                    .build();
        }

        try{
            Optional<Student> byId = studentRepository.findById(studentDto.getId());

            if (byId.isEmpty()){
                return ResponseDto.<StudentDto>builder()
                        .code(NOT_FOUND_ERROR_CODE)
                        .message(NOT_FOUND)
                        .build();
            }

            Student student = byId.get();

            if (studentDto.getAge() != null){
                student.setAge(studentDto.getAge());
            }
            if (studentDto.getFirstName() != null){
                student.setFirstName(studentDto.getFirstName());
            }
            if (studentDto.getLastName() != null){
                student.setLastName(studentDto.getLastName());
            }
            if (studentDto.getGender() != null){
                student.setGender(studentDto.getGender());
            }
            if (studentDto.getPhoneNumber() != null){
                student.setPhoneNumber(studentDto.getPhoneNumber());
            }
            if (studentDto.getUsername() != null){
                student.setUsername(studentDto.getUsername());
            }
            if (studentDto.getPassword() != null){
                student.setPassword(studentDto.getPassword());
            }
            if (studentDto.getImage() != null){
                String imageUrl = imageService.saveFile(studentDto.getImage());
                student.setImage(imageUrl);
            }

            studentRepository.save(student);

            return ResponseDto.<StudentDto>builder()
                    .message(OK)
                    .code(OK_CODE)
                    .success(true)
                    .data(studentMapper.toDto(student))
                    .build();
        } catch (Exception e){
            return ResponseDto.<StudentDto>builder()
                    .code(DATABASE_ERROR_CODE)
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<StudentDto> delete(Integer id) {
        if (id == null){
            return ResponseDto.<StudentDto>builder()
                    .message("ID is NULL!")
                    .code(VALIDATION_ERROR_CODE)
                    .build();
        }

        try{
            Optional<Student> byId = studentRepository.findById(id);

            if (byId.isEmpty()){
                return ResponseDto.<StudentDto>builder()
                        .code(NOT_FOUND_ERROR_CODE)
                        .message(NOT_FOUND)
                        .build();
            }

            Student student = byId.get();
            student.setIsActive(false);
            studentRepository.save(student);

            return ResponseDto.<StudentDto>builder()
                    .message(OK)
                    .code(OK_CODE)
                    .success(true)
                    .data(studentMapper.toDto(byId.get()))
                    .build();
        } catch (Exception e){
            return ResponseDto.<StudentDto>builder()
                    .code(DATABASE_ERROR_CODE)
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }
}