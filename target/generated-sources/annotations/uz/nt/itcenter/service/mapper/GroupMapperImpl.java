package uz.nt.itcenter.service.mapper;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import uz.nt.itcenter.dto.GroupDto;
import uz.nt.itcenter.model.Groups;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-02T17:54:10+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.7 (Amazon.com Inc.)"
)
@Component
public class GroupMapperImpl extends GroupMapper {

    @Override
    public GroupDto toDto(Groups groups) {
        if ( groups == null ) {
            return null;
        }

        GroupDto groupDto = new GroupDto();

        groupDto.setId( groups.getId() );
        groupDto.setDirection( groups.getDirection() );
        groupDto.setGroup_number( groups.getGroup_number() );
        groupDto.setTeacher( groups.getTeacher() );
        groupDto.setAssistant( groups.getAssistant() );
        List<DayOfWeek> list = groups.getDay();
        if ( list != null ) {
            groupDto.setDay( new ArrayList<DayOfWeek>( list ) );
        }
        groupDto.setTime( groups.getTime() );
        groupDto.setCreatedAt( groups.getCreatedAt() );
        groupDto.setClassroom_name( groups.getClassroom_name() );

        groupDto.setImageUrl( groups.getImage() );
        groupDto.setImage( null );

        return groupDto;
    }

    @Override
    public Groups toEntity(GroupDto groupDto) {
        if ( groupDto == null ) {
            return null;
        }

        Groups groups = new Groups();

        groups.setId( groupDto.getId() );
        groups.setDirection( groupDto.getDirection() );
        groups.setGroup_number( groupDto.getGroup_number() );
        groups.setTeacher( groupDto.getTeacher() );
        groups.setAssistant( groupDto.getAssistant() );
        List<DayOfWeek> list = groupDto.getDay();
        if ( list != null ) {
            groups.setDay( new ArrayList<DayOfWeek>( list ) );
        }
        groups.setTime( groupDto.getTime() );
        groups.setCreatedAt( groupDto.getCreatedAt() );
        groups.setClassroom_name( groupDto.getClassroom_name() );

        groups.setImage( imageService.saveFile(groupDto.getImage()) );

        return groups;
    }
}
