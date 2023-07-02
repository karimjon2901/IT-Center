package uz.nt.itcenter.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uz.nt.itcenter.dto.GroupDto;
import uz.nt.itcenter.model.Groups;
import uz.nt.itcenter.repository.StudentRepository;
import uz.nt.itcenter.service.ImageService;

@Mapper(componentModel = "spring")
public abstract class GroupMapper implements CommonMapper<GroupDto, Groups> {
    ImageService imageService;
    StudentRepository studentRepository;

    @Override
    @Mapping(target = "imageUrl", expression = "java(groups.getImage())")
    @Mapping(target = "image", expression = "java(null)")
//    @Mapping(target = "students", expression = "java(studentRepository.findAllByGroupIdAndIsActiveIsTrue(groups.getId()))")
    public abstract GroupDto toDto(Groups groups);

    @Override
    @Mapping(target = "image", expression = "java(imageService.saveFile(groupDto.getImage()))")
//    @Mapping(target = "students", expression = "java(groupDto.getStudents().stream().forEach(s -> s.setImage(imageService.saveFile(s.getImage()))))")
    public abstract Groups toEntity(GroupDto groupDto);
}
