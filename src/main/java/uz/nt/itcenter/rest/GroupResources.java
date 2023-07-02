package uz.nt.itcenter.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.nt.itcenter.dto.AddStudentToGroupDto;
import uz.nt.itcenter.dto.GroupDto;
import uz.nt.itcenter.dto.ResponseDto;
import uz.nt.itcenter.dto.StudentDto;
import uz.nt.itcenter.service.GroupService;

import java.util.List;

@RestController
@RequestMapping("/group")
@RequiredArgsConstructor
public class GroupResources {
    private final GroupService groupService;
    @Operation(
            method = "Add new group",
            description = "Need to send GroupDto to this endpoint to create new group",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Group info",
                    content = @Content(mediaType = "application/json"))
    )
    @PostMapping
    public ResponseDto<GroupDto> add(@RequestBody GroupDto groupDto){
        return groupService.add(groupDto);
    }

    @Operation(
            method = "Get group by id",
            description = "Need to send Group id to this endpoint to get group by id",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Group info",
                    content = @Content(mediaType = "application/json"))
    )
    @GetMapping("/{id}")
    public ResponseDto<GroupDto> getById(@PathVariable Integer id){
        return groupService.getById(id);
    }

    @Operation(
            method = "Get all group",
            description = "This endpoint return all group",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Group info",
                    content = @Content(mediaType = "application/json"))
    )
    @GetMapping
    public ResponseDto<Page<GroupDto>> getAll(@RequestParam(defaultValue = "10") Integer size,
                                              @RequestParam(defaultValue = "0") Integer page){
        return groupService.getAll(size, page);
    }

    @Operation(
            method = "Edit group",
            description = "Need to send GroupDto to this endpoint to edit group",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Group info",
                    content = @Content(mediaType = "application/json"))
    )
    @PatchMapping
    public ResponseDto<GroupDto> update(@RequestBody GroupDto groupDto){
        return groupService.update(groupDto);
    }

    @Operation(
            method = "Delete group",
            description = "Need to send Group id to this endpoint to delete group",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Group info",
                    content = @Content(mediaType = "application/json"))
    )
    @DeleteMapping("/{id}")
    public ResponseDto<GroupDto> delete(@PathVariable Integer id){
        return groupService.delete(id);
    }

    @PostMapping("/add-student")
    public ResponseDto<GroupDto> addStudentToGroup(@RequestBody AddStudentToGroupDto addStudentToGroupDto){
        return groupService.addStudentToGroup(addStudentToGroupDto);
    }

    @GetMapping("/get-students")
    public ResponseDto<List<StudentDto>> getStudents(@RequestParam Integer groupId){
        return groupService.getStudents(groupId);
    }
}
