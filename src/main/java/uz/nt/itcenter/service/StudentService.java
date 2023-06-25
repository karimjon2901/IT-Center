package uz.nt.itcenter.service;

import uz.nt.itcenter.dto.ResponseDto;
import uz.nt.itcenter.dto.StudentDto;

import java.util.List;

public interface StudentService {
    ResponseDto<StudentDto> add(StudentDto studentDto);
    ResponseDto<StudentDto> getById(Integer id);
    ResponseDto<List<StudentDto>> getAll();
    ResponseDto<StudentDto> update(StudentDto studentDto);
    ResponseDto<StudentDto> delete(Integer id);
}
