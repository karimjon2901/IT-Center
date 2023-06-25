package uz.nt.itcenter.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.nt.itcenter.dto.AssistantDto;
import uz.nt.itcenter.dto.ResponseDto;
import uz.nt.itcenter.model.Assistant;
import uz.nt.itcenter.repository.AssistantRepository;
import uz.nt.itcenter.service.AssistantService;
import uz.nt.itcenter.service.mapper.AssistantMapper;

import java.util.List;
import java.util.Optional;

import static uz.nt.itcenter.appStatus.AppStatusCodes.*;
import static uz.nt.itcenter.appStatus.AppStatusCodes.NOT_FOUND_ERROR_CODE;
import static uz.nt.itcenter.appStatus.AppStatusMessages.*;

@Service
@RequiredArgsConstructor
public class AssistantServiceImpl implements AssistantService {
    private final AssistantRepository assistantRepository;
    private final AssistantMapper assistantMapper;

    @Override
    public ResponseDto<AssistantDto> add(AssistantDto assistantDto) {
        try{
            assistantRepository.save(assistantMapper.toEntity(assistantDto));
            return ResponseDto.<AssistantDto>builder()
                    .message(OK)
                    .code(OK_CODE)
                    .success(true)
                    .data(assistantDto)
                    .build();
        } catch (Exception e){
            return ResponseDto.<AssistantDto>builder()
                    .code(DATABASE_ERROR_CODE)
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<AssistantDto> getById(Integer id) {
        if (id == null){
            return ResponseDto.<AssistantDto>builder()
                    .message("ID is NULL!")
                    .code(VALIDATION_ERROR_CODE)
                    .build();
        }

        try{
            Optional<Assistant> byId = assistantRepository.findById(id);

            if (byId.isEmpty()){
                return ResponseDto.<AssistantDto>builder()
                        .code(NOT_FOUND_ERROR_CODE)
                        .message(NOT_FOUND)
                        .build();
            }

            return ResponseDto.<AssistantDto>builder()
                    .message(OK)
                    .code(OK_CODE)
                    .success(true)
                    .data(assistantMapper.toDto(byId.get()))
                    .build();
        } catch (Exception e){
            return ResponseDto.<AssistantDto>builder()
                    .code(DATABASE_ERROR_CODE)
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<List<AssistantDto>> getAll() {
        try{
            return ResponseDto.<List<AssistantDto>>builder()
                    .message(OK)
                    .code(OK_CODE)
                    .success(true)
                    .data(assistantRepository.findAllByIsActiveIsTrue().stream().map(assistantMapper::toDto).toList())
                    .build();
        } catch (Exception e){
            return ResponseDto.<List<AssistantDto>>builder()
                    .code(DATABASE_ERROR_CODE)
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<AssistantDto> update(AssistantDto assistantDto) {
        if (assistantDto.getId() == null){
            return ResponseDto.<AssistantDto>builder()
                    .message("ID is NULL!")
                    .code(VALIDATION_ERROR_CODE)
                    .build();
        }

        try{
            Optional<Assistant> byId = assistantRepository.findById(assistantDto.getId());

            if (byId.isEmpty()){
                return ResponseDto.<AssistantDto>builder()
                        .code(NOT_FOUND_ERROR_CODE)
                        .message(NOT_FOUND)
                        .build();
            }

            Assistant assistant = byId.get();

            if (assistantDto.getAge() != null){
                assistant.setAge(assistantDto.getAge());
            }
            if (assistantDto.getFirstName() != null){
                assistant.setFirstName(assistantDto.getFirstName());
            }
            if (assistantDto.getLastName() != null){
                assistant.setLastName(assistantDto.getLastName());
            }
            if (assistantDto.getGender() != null){
                assistant.setGender(assistantDto.getGender());
            }
            if (assistantDto.getPhoneNumber() != null){
                assistant.setPhoneNumber(assistantDto.getPhoneNumber());
            }
            if (assistantDto.getUsername() != null){
                assistant.setUsername(assistantDto.getUsername());
            }
            if (assistantDto.getPassword() != null){
                assistant.setPassword(assistantDto.getPassword());
            }
            if (assistantDto.getDirection() != null){
                assistant.setDirection(assistantDto.getDirection());
            }

            assistantRepository.save(assistant);

            return ResponseDto.<AssistantDto>builder()
                    .message(OK)
                    .code(OK_CODE)
                    .success(true)
                    .data(assistantMapper.toDto(assistant))
                    .build();
        } catch (Exception e){
            return ResponseDto.<AssistantDto>builder()
                    .code(DATABASE_ERROR_CODE)
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<AssistantDto> delete(Integer id) {
        if (id == null){
            return ResponseDto.<AssistantDto>builder()
                    .message("ID is NULL!")
                    .code(VALIDATION_ERROR_CODE)
                    .build();
        }

        try{
            Optional<Assistant> byId = assistantRepository.findById(id);

            if (byId.isEmpty()){
                return ResponseDto.<AssistantDto>builder()
                        .code(NOT_FOUND_ERROR_CODE)
                        .message(NOT_FOUND)
                        .build();
            }

            Assistant assistant = byId.get();
            assistant.setIsActive(false);
            assistantRepository.save(assistant);

            return ResponseDto.<AssistantDto>builder()
                    .message(OK)
                    .code(OK_CODE)
                    .success(true)
                    .data(assistantMapper.toDto(byId.get()))
                    .build();
        } catch (Exception e){
            return ResponseDto.<AssistantDto>builder()
                    .code(DATABASE_ERROR_CODE)
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }
}