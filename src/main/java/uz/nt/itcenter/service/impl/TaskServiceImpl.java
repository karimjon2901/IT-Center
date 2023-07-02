package uz.nt.itcenter.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import uz.nt.itcenter.dto.ResponseDto;
import uz.nt.itcenter.dto.TaskDto;
import uz.nt.itcenter.model.Task;
import uz.nt.itcenter.repository.TaskRepository;
import uz.nt.itcenter.service.TaskService;
import uz.nt.itcenter.service.mapper.TaskMapper;

import java.util.Optional;

import static uz.nt.itcenter.appStatus.AppStatusCodes.*;
import static uz.nt.itcenter.appStatus.AppStatusMessages.*;
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    @Override
    public ResponseDto<Page<TaskDto>> getAll(Integer size, Integer page) {
        Long count = taskRepository.count();

        PageRequest pageRequest = PageRequest.of(
                (count / size) <= page ?
                        (count % size == 0 ? (int) (count / size) - 1
                                : (int) (count / size))
                        : page,
                size
        );
        try{
            Page<TaskDto> all = taskRepository.findAll(pageRequest).map(taskMapper::toDto);
            return ResponseDto.<Page<TaskDto>>builder()
                    .message(OK)
                    .code(OK_CODE)
                    .success(true)
                    .data(all)
                    .build();
        } catch (Exception e){
            return ResponseDto.<Page<TaskDto>>builder()
                    .code(DATABASE_ERROR_CODE)
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<TaskDto> getById(Integer id) {
        if (id == null){
            return ResponseDto.<TaskDto>builder()
                    .message("ID is NULL!")
                    .code(VALIDATION_ERROR_CODE)
                    .build();
        }

        try{
            Optional<Task> byId = taskRepository.findById(id);

            if (byId.isEmpty()){
                return ResponseDto.<TaskDto>builder()
                        .code(NOT_FOUND_ERROR_CODE)
                        .message(NOT_FOUND)
                        .build();
            }

            return ResponseDto.<TaskDto>builder()
                    .message(OK)
                    .code(OK_CODE)
                    .success(true)
                    .data(taskMapper.toDto(byId.get()))
                    .build();
        } catch (Exception e){
            return ResponseDto.<TaskDto>builder()
                    .code(DATABASE_ERROR_CODE)
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<TaskDto> add(TaskDto taskDto) {
        try{
            taskRepository.save(taskMapper.toEntity(taskDto));
            return ResponseDto.<TaskDto>builder()
                    .message(OK)
                    .code(OK_CODE)
                    .success(true)
                    .data(taskDto)
                    .build();
        } catch (Exception e){
            return ResponseDto.<TaskDto>builder()
                    .code(DATABASE_ERROR_CODE)
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<TaskDto> update(TaskDto taskDto) {
        if (taskDto.getId() == null){
            return ResponseDto.<TaskDto>builder()
                    .message("ID is NULL!")
                    .code(VALIDATION_ERROR_CODE)
                    .build();
        }

        try{
            Optional<Task> byId = taskRepository.findById(taskDto.getId());

            if (byId.isEmpty()){
                return ResponseDto.<TaskDto>builder()
                        .code(NOT_FOUND_ERROR_CODE)
                        .message(NOT_FOUND)
                        .build();
            }

            Task task = byId.get();

            if (taskDto.getName() != null){
                task.setName(taskDto.getName());
            }
            if (taskDto.getGroups() != null){
                task.setGroups(taskDto.getGroups());
            }
            if (taskDto.getDate() != null){
                task.setDate(taskDto.getDate());
            }
            if (taskDto.getDescription() != null){
                task.setDescription(taskDto.getDescription());
            }

            taskRepository.save(task);

            return ResponseDto.<TaskDto>builder()
                    .message(OK)
                    .code(OK_CODE)
                    .success(true)
                    .data(taskMapper.toDto(task))
                    .build();
        } catch (Exception e){
            return ResponseDto.<TaskDto>builder()
                    .code(DATABASE_ERROR_CODE)
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<TaskDto> delete(Integer id) {
        if (id == null){
            return ResponseDto.<TaskDto>builder()
                    .message("ID is NULL!")
                    .code(VALIDATION_ERROR_CODE)
                    .build();
        }

        try{
            Optional<Task> byId = taskRepository.findById(id);

            if (byId.isEmpty()){
                return ResponseDto.<TaskDto>builder()
                        .code(NOT_FOUND_ERROR_CODE)
                        .message(NOT_FOUND)
                        .build();
            }

            Task task = byId.get();
            task.setIsActive(false);
            taskRepository.save(task);

            return ResponseDto.<TaskDto>builder()
                    .message(OK)
                    .code(OK_CODE)
                    .success(true)
                    .data(taskMapper.toDto(byId.get()))
                    .build();
        } catch (Exception e){
            return ResponseDto.<TaskDto>builder()
                    .code(DATABASE_ERROR_CODE)
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }
}
