package uz.nt.itcenter.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import uz.nt.itcenter.dto.GroupDto;
import uz.nt.itcenter.model.Group;
import uz.nt.itcenter.service.ImageService;
import uz.nt.itcenter.service.impl.ImageServiceImpl;

@Mapper(componentModel = "spring")
public abstract class GroupMapper implements CommonMapper<GroupDto, Group> {
    @Autowired
    ImageService imageService;
    @Override
    @Mapping(target = "image", expression = "java(imageService.getImage(group.getImage()))")
    public abstract GroupDto toDto(Group group);

    @Override
    @Mapping(target = "image", expression = "java(imageService.saveFile(groupDto.getImage()))")
    public abstract Group toEntity(GroupDto groupDto);
}
