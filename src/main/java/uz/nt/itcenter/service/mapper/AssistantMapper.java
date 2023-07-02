package uz.nt.itcenter.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uz.nt.itcenter.dto.AssistantDto;
import uz.nt.itcenter.model.Assistant;
import uz.nt.itcenter.service.ImageService;

@Mapper(componentModel = "spring")
public abstract class AssistantMapper implements CommonMapper<AssistantDto, Assistant>{
    ImageService imageService;
    @Override
    @Mapping(target = "imageUrl", expression = "java(assistant.getImage())")
    @Mapping(target = "image", expression = "java(null)")
    public abstract AssistantDto toDto(Assistant assistant);

    @Override
    @Mapping(target = "image", expression = "java(imageService.saveFile(assistantDto.getImage()))")
    public abstract Assistant toEntity(AssistantDto assistantDto);
}
