package uz.nt.itcenter.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.nt.itcenter.dto.ResponseDto;
import uz.nt.itcenter.dto.TaskDto;
import uz.nt.itcenter.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskResources {
    private final TaskService taskService;
    @Operation(
            method = "Add new Task",
            description = "Need to send TaskDto to this endpoint to create new task",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Task info",
                    content = @Content(mediaType = "application/json"))
    )
    @PostMapping
    public ResponseDto<TaskDto> add(@RequestBody TaskDto taskDto){
        return taskService.add(taskDto);
    }

    @Operation(
            method = "Get all tasks",
            description = "This endpoint return all tasks",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Task info",
                    content = @Content(mediaType = "application/json"))
    )
    @GetMapping
    public ResponseDto<Page<TaskDto>> getAll(@RequestParam(defaultValue = "10") Integer size,
                                             @RequestParam(defaultValue = "0") Integer page){
        return taskService.getAll(size, page);
    }

    @Operation(
            method = "Get task by id",
            description = "Need to send Task id to this endpoint to get task by id",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Task info",
                    content = @Content(mediaType = "application/json"))
    )
    @GetMapping("/{id}")
    public ResponseDto<TaskDto> getById(@PathVariable Integer id){
        return taskService.getById(id);
    }

    @Operation(
            method = "Edit task",
            description = "Need to send TaskDto to this endpoint to edit task",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Task info",
                    content = @Content(mediaType = "application/json"))
    )
    @PatchMapping
    public ResponseDto<TaskDto> update(@RequestBody TaskDto taskDto){
        return taskService.update(taskDto);
    }

    @Operation(
            method = "Delete Task",
            description = "Need to send Task id to this endpoint to delete Task",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Task info",
                    content = @Content(mediaType = "application/json"))
    )
    @DeleteMapping("/{id}")
    public ResponseDto<TaskDto> delete(@PathVariable Integer id){
        return taskService.delete(id);
    }
}
