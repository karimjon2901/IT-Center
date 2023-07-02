package uz.nt.itcenter.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.nt.itcenter.dto.ResponseDto;
import uz.nt.itcenter.dto.StatisticsDto;
import uz.nt.itcenter.repository.AssistantRepository;
import uz.nt.itcenter.repository.GroupRepository;
import uz.nt.itcenter.repository.StudentRepository;
import static uz.nt.itcenter.appStatus.AppStatusCodes.*;
import static uz.nt.itcenter.appStatus.AppStatusMessages.*;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticsResources {
    private final GroupRepository groupRepository;
    private final StudentRepository studentRepository;
    private final AssistantRepository assistantRepository;

    @GetMapping
    public ResponseDto<StatisticsDto> getStatistics(){
        StatisticsDto statisticsDto = new StatisticsDto();

        statisticsDto.setGroups(groupRepository.findAllByIsActiveIsTrue().size());
        statisticsDto.setStudents(studentRepository.findAllByIsActiveIsTrue().size());
        statisticsDto.setAssistants(assistantRepository.findAllByIsActiveIsTrue().size());

        return ResponseDto.<StatisticsDto>builder()
                .message(OK)
                .code(OK_CODE)
                .success(true)
                .data(statisticsDto)
                .build();
    }
}
