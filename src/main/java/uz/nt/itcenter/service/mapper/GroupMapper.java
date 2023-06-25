package uz.nt.itcenter.service.mapper;

import org.mapstruct.Mapper;
import uz.nt.itcenter.dto.GroupDto;
import uz.nt.itcenter.model.Group;
@Mapper(componentModel = "spring")
public abstract class GroupMapper implements CommonMapper<GroupDto, Group>{
    @Override
    public abstract GroupDto toDto(Group group);

    @Override
    public abstract Group toEntity(GroupDto groupDto);
}
