package uz.nt.itcenter.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.nt.itcenter.dto.RatingDto;
import uz.nt.itcenter.dto.ResponseDto;
import uz.nt.itcenter.service.RatingService;


@RestController
@RequestMapping("/rating")
@RequiredArgsConstructor
public class RatingResources {
    private final RatingService ratingService;

    @Operation(
            method = "Get all ratings",
            description = "This endpoint return all ratings",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Rating info",
                    content = @Content(mediaType = "application/json"))
    )
    @GetMapping
    public ResponseDto<Page<RatingDto>> getAll(@RequestParam(defaultValue = "10") Integer size,
                                               @RequestParam(defaultValue = "0") Integer page){
        return ratingService.getAll(size, page);
    }

    @Operation(
            method = "Get Rating by id",
            description = "Need to send Rating id to this endpoint to get rating by id",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Rating info",
                    content = @Content(mediaType = "application/json"))
    )
    @GetMapping("/{id}")
    public ResponseDto<RatingDto> getById(@PathVariable Integer id){
        return ratingService.getById(id);
    }

    @Operation(
            method = "Add new rating",
            description = "Need to send RatingDto to this endpoint to create new rating",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Rating info",
                    content = @Content(mediaType = "application/json"))
    )
    @PostMapping
    public ResponseDto<RatingDto> add(@RequestBody RatingDto ratingDto){
        return ratingService.add(ratingDto);
    }

    @Operation(
            method = "Edit rating",
            description = "Need to send RatingDto to this endpoint to edit rating",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Rating info",
                    content = @Content(mediaType = "application/json"))
    )
    @PatchMapping
    public ResponseDto<RatingDto> update(@RequestBody RatingDto ratingDto){
        return ratingService.update(ratingDto);
    }
}
