package uz.nt.itcenter.service;

import org.springframework.data.domain.Page;
import uz.nt.itcenter.dto.AddStudentToGroupDto;
import uz.nt.itcenter.dto.GroupDto;
import uz.nt.itcenter.dto.ResponseDto;
import uz.nt.itcenter.dto.StudentDto;

import java.util.List;

public interface GroupService {

    ResponseDto<GroupDto> add(GroupDto groupDto);

    ResponseDto<GroupDto> getById(Integer id);

    ResponseDto<Page<GroupDto>> getAll(Integer size, Integer page);

    ResponseDto<GroupDto> update(GroupDto groupDto);

    ResponseDto<GroupDto> delete(Integer id);

    ResponseDto<GroupDto> addStudentToGroup(AddStudentToGroupDto addStudentToGroupDto);

    ResponseDto<List<StudentDto>> getStudents(Integer groupId);
}
