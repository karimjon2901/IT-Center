package uz.nt.itcenter.service.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import uz.nt.itcenter.dto.AssistantDto;
import uz.nt.itcenter.model.Assistant;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-02T23:31:31+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.7 (Amazon.com Inc.)"
)
@Component
public class AssistantMapperImpl extends AssistantMapper {

    @Override
    public AssistantDto toDto(Assistant assistant) {
        if ( assistant == null ) {
            return null;
        }

        AssistantDto assistantDto = new AssistantDto();

        assistantDto.setId( assistant.getId() );
        assistantDto.setFirstName( assistant.getFirstName() );
        assistantDto.setLastName( assistant.getLastName() );
        assistantDto.setAge( assistant.getAge() );
        assistantDto.setPhoneNumber( assistant.getPhoneNumber() );
        assistantDto.setDirection( assistant.getDirection() );
        assistantDto.setUsername( assistant.getUsername() );
        assistantDto.setPassword( assistant.getPassword() );
        assistantDto.setGender( assistant.getGender() );

        assistantDto.setImageUrl( assistant.getImage() );
        assistantDto.setImage( null );

        return assistantDto;
    }

    @Override
    public Assistant toEntity(AssistantDto assistantDto) {
        if ( assistantDto == null ) {
            return null;
        }

        Assistant assistant = new Assistant();

        assistant.setId( assistantDto.getId() );
        assistant.setFirstName( assistantDto.getFirstName() );
        assistant.setLastName( assistantDto.getLastName() );
        assistant.setAge( assistantDto.getAge() );
        assistant.setPhoneNumber( assistantDto.getPhoneNumber() );
        assistant.setDirection( assistantDto.getDirection() );
        assistant.setUsername( assistantDto.getUsername() );
        assistant.setPassword( assistantDto.getPassword() );
        assistant.setGender( assistantDto.getGender() );

        assistant.setImage( imageService.saveFile(assistantDto.getImage()) );

        return assistant;
    }
}
