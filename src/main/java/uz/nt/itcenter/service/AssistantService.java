package uz.nt.itcenter.service;

import org.springframework.data.domain.Page;
import uz.nt.itcenter.dto.AssistantDto;
import uz.nt.itcenter.dto.GroupDto;
import uz.nt.itcenter.dto.ResponseDto;

import java.util.List;

public interface AssistantService {
    ResponseDto<AssistantDto> add(AssistantDto assistantDto);
    ResponseDto<AssistantDto> getById(Integer id);
    ResponseDto<Page<AssistantDto>> getAll(Integer size, Integer page);
    ResponseDto<AssistantDto> update(AssistantDto assistantDto);
    ResponseDto<AssistantDto> delete(Integer id);

    ResponseDto<List<GroupDto>> getGroups(Integer id);
}
