package uz.nt.itcenter.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import uz.nt.itcenter.dto.AssistantDto;
import uz.nt.itcenter.dto.GroupDto;
import uz.nt.itcenter.dto.ResponseDto;
import uz.nt.itcenter.model.Assistant;
import uz.nt.itcenter.repository.AssistantRepository;
import uz.nt.itcenter.repository.GroupRepository;
import uz.nt.itcenter.service.AssistantService;
import uz.nt.itcenter.service.mapper.AssistantMapper;
import uz.nt.itcenter.service.mapper.GroupMapper;

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
    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;

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
    public ResponseDto<Page<AssistantDto>> getAll(Integer size, Integer page) {
        Long count = assistantRepository.count();

        PageRequest pageRequest = PageRequest.of(
                (count / size) <= page ?
                        (count % size == 0 ? (int) (count / size) - 1
                                : (int) (count / size))
                        : page,
                size
        );
        try{
            Page<AssistantDto> all = assistantRepository.findAll(pageRequest).map(assistantMapper::toDto);
            return ResponseDto.<Page<AssistantDto>>builder()
                    .message(OK)
                    .code(OK_CODE)
                    .success(true)
                    .data(all)
                    .build();
        } catch (Exception e){
            return ResponseDto.<Page<AssistantDto>>builder()
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

    @Override
    public ResponseDto<List<GroupDto>> getGroups(Integer id) {
        if (id == null){
            return ResponseDto.<List<GroupDto>>builder()
                    .message(NULL_VALUE)
                    .code(VALIDATION_ERROR_CODE)
                    .build();
        }

        return ResponseDto.<List<GroupDto>>builder()
                .message(OK)
                .success(true)
                .code(OK_CODE)
                .data(groupRepository.findAllByIsActiveIsTrueAndAssistantId(id).stream().map(groupMapper::toDto).toList())
                .build();
    }
}