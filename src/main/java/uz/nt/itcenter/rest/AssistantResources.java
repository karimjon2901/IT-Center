package uz.nt.itcenter.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.nt.itcenter.dto.AssistantDto;
import uz.nt.itcenter.dto.ResponseDto;
import uz.nt.itcenter.dto.StudentDto;
import uz.nt.itcenter.service.AssistantService;

import java.util.List;

@RestController
@RequestMapping("/assistant")
@RequiredArgsConstructor
public class AssistantResources {
    private final AssistantService assistantService;

    @PostMapping
    public ResponseDto<AssistantDto> add(@RequestBody AssistantDto assistantDto){
        return assistantService.add(assistantDto);
    }
    @GetMapping("/{id}")
    public ResponseDto<AssistantDto> getById(@PathVariable Integer id){
        return assistantService.getById(id);
    }

    @GetMapping
    public ResponseDto<List<AssistantDto>> getAll(){
        return assistantService.getAll();
    }

    @PatchMapping
    public ResponseDto<AssistantDto> update(@RequestBody AssistantDto assistantDto){
        return assistantService.update(assistantDto);
    }

    @DeleteMapping("/{id}")
    public ResponseDto<AssistantDto> delete(@PathVariable Integer id){
        return assistantService.delete(id);
    }
}
