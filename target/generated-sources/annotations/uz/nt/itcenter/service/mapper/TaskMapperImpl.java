package uz.nt.itcenter.service.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import uz.nt.itcenter.dto.TaskDto;
import uz.nt.itcenter.model.Task;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-02T07:06:17+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.7 (Amazon.com Inc.)"
)
@Component
public class TaskMapperImpl extends TaskMapper {

    @Override
    public TaskDto toDto(Task task) {
        if ( task == null ) {
            return null;
        }

        TaskDto taskDto = new TaskDto();

        taskDto.setId( task.getId() );
        taskDto.setName( task.getName() );
        taskDto.setDescription( task.getDescription() );
        taskDto.setCreatedAt( task.getCreatedAt() );
        taskDto.setDate( task.getDate() );
        taskDto.setGroups( task.getGroups() );

        return taskDto;
    }

    @Override
    public Task toEntity(TaskDto taskDto) {
        if ( taskDto == null ) {
            return null;
        }

        Task task = new Task();

        task.setId( taskDto.getId() );
        task.setName( taskDto.getName() );
        task.setDescription( taskDto.getDescription() );
        task.setCreatedAt( taskDto.getCreatedAt() );
        task.setDate( taskDto.getDate() );
        task.setGroups( taskDto.getGroups() );

        return task;
    }
}
