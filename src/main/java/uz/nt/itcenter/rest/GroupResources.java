package uz.nt.itcenter.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping
    public ResponseDto<GroupDto> add(@RequestBody GroupDto groupDto){
        return groupService.add(groupDto);
    }
    @GetMapping("/{id}")
    public ResponseDto<GroupDto> getById(@PathVariable Integer id){
        return groupService.getById(id);
    }

    @GetMapping
    public ResponseDto<List<GroupDto>> getAll(){
        return groupService.getAll();
    }

    @PatchMapping
    public ResponseDto<GroupDto> update(@RequestBody GroupDto groupDto){
        return groupService.update(groupDto);
    }

    @DeleteMapping("/{id}")
    public ResponseDto<GroupDto> delete(@PathVariable Integer id){
        return groupService.delete(id);
    }
}
