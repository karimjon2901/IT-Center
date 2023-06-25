package uz.nt.itcenter.service.mapper;

import org.mapstruct.Mapper;
import uz.nt.itcenter.dto.AssistantDto;
import uz.nt.itcenter.model.Assistant;
@Mapper(componentModel = "spring")
public abstract class AssistantMapper implements CommonMapper<AssistantDto, Assistant>{
    @Override
    public abstract AssistantDto toDto(Assistant assistant);

    @Override
    public abstract Assistant toEntity(AssistantDto assistantDto);
}
