package uz.nt.itcenter.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.nt.itcenter.dto.AssistantDto;
import uz.nt.itcenter.dto.GroupDto;
import uz.nt.itcenter.dto.ResponseDto;
import uz.nt.itcenter.service.AssistantService;

import java.util.List;

@RestController
@RequestMapping("/assistant")
@RequiredArgsConstructor
public class AssistantResources {
    private final AssistantService assistantService;
    @Operation(
            method = "Add new assistant",
            description = "Need to send AssistantDto to this endpoint to create new assistant",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Assistant info",
                    content = @Content(mediaType = "application/json"))
    )
    @PostMapping
    public ResponseDto<AssistantDto> add(@RequestBody AssistantDto assistantDto){
        return assistantService.add(assistantDto);
    }

    @Operation(
            method = "Get assistant by id",
            description = "Need to send Assistant id to this endpoint to get assistant by id",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Assistant info",
                    content = @Content(mediaType = "application/json"))
    )
    @GetMapping("/{id}")
    public ResponseDto<AssistantDto> getById(@PathVariable Integer id){
        return assistantService.getById(id);
    }

    @Operation(
            method = "Get all assistants",
            description = "This endpoint return all assistants",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Assistant info",
                    content = @Content(mediaType = "application/json"))
    )
    @GetMapping
    public ResponseDto<Page<AssistantDto>> getAll(@RequestParam(defaultValue = "10") Integer size,
                                                  @RequestParam(defaultValue = "0") Integer page){
        return assistantService.getAll(size,page);
    }

    @Operation(
            method = "Edit assistant",
            description = "Need to send AssistantDto to this endpoint to edit assistant",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Assistant info",
                    content = @Content(mediaType = "application/json"))
    )
    @PatchMapping
    public ResponseDto<AssistantDto> update(@RequestBody AssistantDto assistantDto){
        return assistantService.update(assistantDto);
    }

    @Operation(
            method = "Delete assistant",
            description = "Need to send Assistant id to this endpoint to delete assistant",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Assistant info",
                    content = @Content(mediaType = "application/json"))
    )
    @DeleteMapping("/{id}")
    public ResponseDto<AssistantDto> delete(@PathVariable Integer id){
        return assistantService.delete(id);
    }

    public ResponseDto<List<GroupDto>> getGroups(@RequestParam Integer id){
        return assistantService.getGroups(id);
    }
}
