package uz.nt.itcenter.rest;

import lombok.RequiredArgsConstructor;
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
    @PostMapping
    public ResponseDto<StudentDto> add(@RequestBody StudentDto studentDto){
        return studentService.add(studentDto);
    }
    @GetMapping("/{id}")
    public ResponseDto<StudentDto> getById(@PathVariable Integer id){
        return studentService.getById(id);
    }

    @GetMapping
    public ResponseDto<List<StudentDto>> getAll(){
        return studentService.getAll();
    }

    @PatchMapping
    public ResponseDto<StudentDto> update(@RequestBody StudentDto studentDto){
        return studentService.update(studentDto);
    }

    @DeleteMapping("/{id}")
    public ResponseDto<StudentDto> delete(@PathVariable Integer id){
        return studentService.delete(id);
    }
}
