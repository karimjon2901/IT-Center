package uz.nt.itcenter.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.nt.itcenter.dto.ResponseDto;
import uz.nt.itcenter.dto.StudentDto;
import uz.nt.itcenter.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentResources {
    private final StudentService studentService;
    @Operation(
            method = "Add new student",
            description = "Need to send StudentDto to this endpoint to create new student",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Student info",
                    content = @Content(mediaType = "application/json"))
    )
    @PostMapping
    public ResponseDto<StudentDto> add(@RequestBody StudentDto studentDto){
        return studentService.add(studentDto);
    }

    @Operation(
            method = "Get student by id",
            description = "Need to send Student id to this endpoint to get student by id",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Student info",
                    content = @Content(mediaType = "application/json"))
    )
    @GetMapping("/{id}")
    public ResponseDto<StudentDto> getById(@PathVariable Integer id){
        return studentService.getById(id);
    }

    @Operation(
            method = "Get all students",
            description = "This endpoint return all students",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Student info",
                    content = @Content(mediaType = "application/json"))
    )
    @GetMapping
    public ResponseDto<Page<StudentDto>> getAll(@RequestParam(defaultValue = "10") Integer size,
                                                @RequestParam(defaultValue = "0") Integer page){
        return studentService.getAll(size, page);
    }

    @Operation(
            method = "Edit student",
            description = "Need to send StudentDto to this endpoint to edit student",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Student info",
                    content = @Content(mediaType = "application/json"))
    )
    @PatchMapping
    public ResponseDto<StudentDto> update(@RequestBody StudentDto studentDto){
        return studentService.update(studentDto);
    }

    @Operation(
            method = "Delete student",
            description = "Need to send Student id to this endpoint to delete student",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Student info",
                    content = @Content(mediaType = "application/json"))
    )
    @DeleteMapping("/{id}")
    public ResponseDto<StudentDto> delete(@PathVariable Integer id){
        return studentService.delete(id);
    }
}
