package uz.nt.itcenter.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.nt.itcenter.dto.GroupDto;
import uz.nt.itcenter.dto.ResponseDto;
import uz.nt.itcenter.model.Group;
import uz.nt.itcenter.repository.GroupRepository;
import uz.nt.itcenter.service.GroupService;
import uz.nt.itcenter.service.ImageService;
import uz.nt.itcenter.service.mapper.GroupMapper;

import java.util.List;
import java.util.Optional;

import static uz.nt.itcenter.appStatus.AppStatusCodes.*;
import static uz.nt.itcenter.appStatus.AppStatusMessages.*;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;
    private final ImageService imageService;

    @Override
    public ResponseDto<GroupDto> add(GroupDto groupDto) {
        try{
            groupRepository.save(groupMapper.toEntity(groupDto));
            return ResponseDto.<GroupDto>builder()
                    .message(OK)
                    .code(OK_CODE)
                    .success(true)
                    .data(groupDto)
                    .build();
        } catch (Exception e){
            return ResponseDto.<GroupDto>builder()
                    .code(DATABASE_ERROR_CODE)
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<GroupDto> getById(Integer id) {
        if (id == null){
            return ResponseDto.<GroupDto>builder()
                    .message("ID is NULL!")
                    .code(VALIDATION_ERROR_CODE)
                    .build();
        }

        try{
            Optional<Group> byId = groupRepository.findById(id);

            if (byId.isEmpty()){
                return ResponseDto.<GroupDto>builder()
                        .code(NOT_FOUND_ERROR_CODE)
                        .message(NOT_FOUND)
                        .build();
            }

            return ResponseDto.<GroupDto>builder()
                    .message(OK)
                    .code(OK_CODE)
                    .success(true)
                    .data(groupMapper.toDto(byId.get()))
                    .build();
        } catch (Exception e){
            return ResponseDto.<GroupDto>builder()
                    .code(DATABASE_ERROR_CODE)
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<List<GroupDto>> getAll() {
        try{
            return ResponseDto.<List<GroupDto>>builder()
                    .message(OK)
                    .code(OK_CODE)
                    .success(true)
                    .data(groupRepository.findAllByIsActiveIsTrue().stream().map(groupMapper::toDto).toList())
                    .build();
        } catch (Exception e){
            return ResponseDto.<List<GroupDto>>builder()
                    .code(DATABASE_ERROR_CODE)
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<GroupDto> update(GroupDto groupDto) {
        if (groupDto.getId() == null){
            return ResponseDto.<GroupDto>builder()
                    .message("ID is NULL!")
                    .code(VALIDATION_ERROR_CODE)
                    .build();
        }

        try{
            Optional<Group> byId = groupRepository.findById(groupDto.getId());

            if (byId.isEmpty()){
                return ResponseDto.<GroupDto>builder()
                        .code(NOT_FOUND_ERROR_CODE)
                        .message(NOT_FOUND)
                        .build();
            }

            Group group = byId.get();

            if (groupDto.getGroup_number() != null){
                group.setGroup_number(groupDto.getGroup_number());
            }
            if (groupDto.getDirection() != null){
                group.setDirection(groupDto.getDirection());
            }
            if (groupDto.getAssistant() != null){
                group.setAssistant(groupDto.getAssistant());
            }
            if (groupDto.getTeacher() != null){
                group.setTeacher(groupDto.getTeacher());
            }
            if (groupDto.getTime() != null){
                group.setTime(groupDto.getTime());
            }
            if (groupDto.getClassroom_name() != null){
                group.setClassroom_name(groupDto.getClassroom_name());
            }
            if (groupDto.getDay() != null){
                group.setDay(groupDto.getDay());
            }
            if (groupDto.getImage() != null){
                String imageUrl = imageService.saveFile(groupDto.getImage());
                group.setImage(imageUrl);
            }

            groupRepository.save(group);

            return ResponseDto.<GroupDto>builder()
                    .message(OK)
                    .code(OK_CODE)
                    .success(true)
                    .data(groupMapper.toDto(group))
                    .build();
        } catch (Exception e){
            return ResponseDto.<GroupDto>builder()
                    .code(DATABASE_ERROR_CODE)
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<GroupDto> delete(Integer id) {
        if (id == null){
            return ResponseDto.<GroupDto>builder()
                    .message("ID is NULL!")
                    .code(VALIDATION_ERROR_CODE)
                    .build();
        }

        try{
            Optional<Group> byId = groupRepository.findById(id);

            if (byId.isEmpty()){
                return ResponseDto.<GroupDto>builder()
                        .code(NOT_FOUND_ERROR_CODE)
                        .message(NOT_FOUND)
                        .build();
            }

            Group group = byId.get();
            group.setIsActive(false);
            groupRepository.save(group);

            return ResponseDto.<GroupDto>builder()
                    .message(OK)
                    .code(OK_CODE)
                    .success(true)
                    .data(groupMapper.toDto(byId.get()))
                    .build();
        } catch (Exception e){
            return ResponseDto.<GroupDto>builder()
                    .code(DATABASE_ERROR_CODE)
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }
}