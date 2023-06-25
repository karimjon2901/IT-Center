package uz.nt.itcenter.service;

import uz.nt.itcenter.dto.GroupDto;
import uz.nt.itcenter.dto.ResponseDto;

import java.util.List;

public interface GroupService {

    ResponseDto<GroupDto> add(GroupDto groupDto);

    ResponseDto<GroupDto> getById(Integer id);

    ResponseDto<List<GroupDto>> getAll();

    ResponseDto<GroupDto> update(GroupDto groupDto);

    ResponseDto<GroupDto> delete(Integer id);
}
