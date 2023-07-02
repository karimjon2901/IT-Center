package uz.nt.itcenter.service;

import org.springframework.data.domain.Page;
import uz.nt.itcenter.dto.ResponseDto;
import uz.nt.itcenter.dto.TaskDto;


public interface TaskService {
    ResponseDto<Page<TaskDto>> getAll(Integer size,Integer page);

    ResponseDto<TaskDto> getById(Integer id);

    ResponseDto<TaskDto> add(TaskDto taskDto);

    ResponseDto<TaskDto> update(TaskDto taskDto);

    ResponseDto<TaskDto> delete(Integer id);
}
