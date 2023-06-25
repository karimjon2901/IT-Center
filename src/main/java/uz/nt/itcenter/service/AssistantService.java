package uz.nt.itcenter.service;

import uz.nt.itcenter.dto.AssistantDto;
import uz.nt.itcenter.dto.ResponseDto;

import java.util.List;

public interface AssistantService {
    ResponseDto<AssistantDto> add(AssistantDto assistantDto);
    ResponseDto<AssistantDto> getById(Integer id);
    ResponseDto<List<AssistantDto>> getAll();
    ResponseDto<AssistantDto> update(AssistantDto assistantDto);
    ResponseDto<AssistantDto> delete(Integer id);
}
