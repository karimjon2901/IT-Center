package uz.nt.itcenter.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.nt.itcenter.dto.ResponseDto;
import uz.nt.itcenter.dto.StudentDto;
import uz.nt.itcenter.model.Student;
import uz.nt.itcenter.repository.StudentRepository;
import uz.nt.itcenter.service.StudentService;
import uz.nt.itcenter.service.mapper.StudentMapper;

import java.util.List;
import java.util.Optional;

import static uz.nt.itcenter.appStatus.AppStatusCodes.*;
import static uz.nt.itcenter.appStatus.AppStatusMessages.*;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Override
    public ResponseDto<StudentDto> add(StudentDto studentDto) {
        try{
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
    public ResponseDto<List<StudentDto>> getAll() {
        try{
            return ResponseDto.<List<StudentDto>>builder()
                    .message(OK)
                    .code(OK_CODE)
                    .success(true)
                    .data(studentRepository.findAllByIsActiveIsTrue().stream().map(studentMapper::toDto).toList())
                    .build();
        } catch (Exception e){
            return ResponseDto.<List<StudentDto>>builder()
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