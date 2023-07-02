package uz.nt.itcenter.service.mapper;

import org.mapstruct.Mapper;
import uz.nt.itcenter.dto.TaskDto;
import uz.nt.itcenter.model.Task;
@Mapper(componentModel = "spring")
public abstract class TaskMapper implements CommonMapper<TaskDto, Task>{
    @Override
    public abstract TaskDto toDto(Task task);

    @Override
    public abstract Task toEntity(TaskDto taskDto);
}
