package uz.nt.itcenter.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import uz.nt.itcenter.dto.AddStudentToGroupDto;
import uz.nt.itcenter.dto.GroupDto;
import uz.nt.itcenter.dto.ResponseDto;
import uz.nt.itcenter.dto.StudentDto;
import uz.nt.itcenter.model.Groups;
import uz.nt.itcenter.model.Student;
import uz.nt.itcenter.repository.GroupRepository;
import uz.nt.itcenter.repository.StudentRepository;
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
    private final StudentRepository studentRepository;

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
            Optional<Groups> byId = groupRepository.findById(id);
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
    public ResponseDto<Page<GroupDto>> getAll(Integer size, Integer page) {
        Long count = groupRepository.count();

        PageRequest pageRequest = PageRequest.of(
                (count / size) <= page ?
                        (count % size == 0 ? (int) (count / size) - 1
                                : (int) (count / size))
                        : page,
                size
        );
        try{
            Page<GroupDto> all = groupRepository.findAll(pageRequest).map(groupMapper::toDto);
            return ResponseDto.<Page<GroupDto>>builder()
                    .message(OK)
                    .code(OK_CODE)
                    .success(true)
                    .data(all)
                    .build();
        } catch (Exception e){
            return ResponseDto.<Page<GroupDto>>builder()
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
            Optional<Groups> byId = groupRepository.findById(groupDto.getId());

            if (byId.isEmpty()){
                return ResponseDto.<GroupDto>builder()
                        .code(NOT_FOUND_ERROR_CODE)
                        .message(NOT_FOUND)
                        .build();
            }

            Groups groups = byId.get();

            if (groupDto.getGroup_number() != null){
                groups.setGroup_number(groupDto.getGroup_number());
            }
            if (groupDto.getDirection() != null){
                groups.setDirection(groupDto.getDirection());
            }
            if (groupDto.getAssistant() != null){
                groups.setAssistant(groupDto.getAssistant());
            }
            if (groupDto.getTeacher() != null){
                groups.setTeacher(groupDto.getTeacher());
            }
            if (groupDto.getTime() != null){
                groups.setTime(groupDto.getTime());
            }
            if (groupDto.getClassroom_name() != null){
                groups.setClassroom_name(groupDto.getClassroom_name());
            }
            if (groupDto.getDay() != null){
                groups.setDay(groupDto.getDay());
            }
            if (groupDto.getImage() != null){
                String imageUrl = imageService.saveFile(groupDto.getImage());
                groups.setImage(imageUrl);
            }

            groupRepository.save(groups);

            return ResponseDto.<GroupDto>builder()
                    .message(OK)
                    .code(OK_CODE)
                    .success(true)
                    .data(groupMapper.toDto(groups))
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
            Optional<Groups> byId = groupRepository.findById(id);

            if (byId.isEmpty()){
                return ResponseDto.<GroupDto>builder()
                        .code(NOT_FOUND_ERROR_CODE)
                        .message(NOT_FOUND)
                        .build();
            }

            Groups groups = byId.get();
            groups.setIsActive(false);
            groupRepository.save(groups);

            return ResponseDto.<GroupDto>builder()
                    .message(OK)
                    .code(OK_CODE)
                    .success(true)
                    .data(groupMapper.toDto(byId.get()))
                    .build();
        } catch (Exception e){
            return ResponseDto.<GroupDto>builder()
                    .code(DATABASE_ERROR_CODE)
                    .message(DATABASE_ERROR + " : " + e. getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<GroupDto> addStudentToGroup(AddStudentToGroupDto addStudentToGroupDto) {
        if (addStudentToGroupDto.getGroupId() == null || addStudentToGroupDto.getStudentId() ==null){
            return ResponseDto.<GroupDto>builder()
                    .message("Id is null!")
                    .code(VALIDATION_ERROR_CODE)
                    .build();
        }

        try{
            Optional<Student> student = studentRepository.findByIdAndIsActiveIsTrue(addStudentToGroupDto.getStudentId());
            Optional<Groups> group = groupRepository.findByIdAndIsActiveIsTrue(addStudentToGroupDto.getGroupId());

            if (student.isEmpty() || group.isEmpty()){
                return ResponseDto.<GroupDto>builder()
                        .message(NOT_FOUND)
                        .code(NOT_FOUND_ERROR_CODE)
                        .build();
            }
            Student student1 = student.get();

            student1.setGroup(group.get());
            studentRepository.save(student1);

            return ResponseDto.<GroupDto>builder()
                    .message(OK)
                    .code(OK_CODE)
                    .success(true)
                    .data(groupMapper.toDto(group.get()))
                    .build();

        }catch (Exception e){
            return ResponseDto.<GroupDto>builder()
                    .code(DATABASE_ERROR_CODE)
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<List<StudentDto>> getStudents(Integer groupId) {
        if (groupId == null){
            return ResponseDto.<List<StudentDto>>builder()
                    .message("Id is null!")
                    .code(VALIDATION_ERROR_CODE)
                    .build();
        }
        try {
            List<StudentDto> studentDtoList = studentRepository.findAllByGroupIdAndIsActiveIsTrue(groupId);

            return ResponseDto.<List<StudentDto>>builder()
                    .message(OK)
                    .code(OK_CODE)
                    .success(true)
                    .data(studentDtoList)
                    .build();
        } catch (Exception e){
            return ResponseDto.<List<StudentDto>>builder()
                    .code(DATABASE_ERROR_CODE)
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }
}