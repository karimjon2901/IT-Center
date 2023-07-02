package uz.nt.itcenter.service;

import org.springframework.data.domain.Page;
import uz.nt.itcenter.dto.ResponseDto;
import uz.nt.itcenter.dto.StudentDto;

import java.util.List;

public interface StudentService {
    ResponseDto<StudentDto> add(StudentDto studentDto);
    ResponseDto<StudentDto> getById(Integer id);
    ResponseDto<Page<StudentDto>> getAll(Integer size, Integer page);
    ResponseDto<StudentDto> update(StudentDto studentDto);
    ResponseDto<StudentDto> delete(Integer id);
}
