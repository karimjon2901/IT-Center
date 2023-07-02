package uz.nt.itcenter.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import uz.nt.itcenter.dto.RatingDto;
import uz.nt.itcenter.dto.ResponseDto;
import uz.nt.itcenter.model.Rating;
import uz.nt.itcenter.repository.RatingRepository;
import uz.nt.itcenter.repository.StudentRepository;
import uz.nt.itcenter.repository.TaskRepository;
import uz.nt.itcenter.service.RatingService;
import uz.nt.itcenter.service.mapper.RatingMapper;

import java.util.Optional;

import static uz.nt.itcenter.appStatus.AppStatusCodes.*;
import static uz.nt.itcenter.appStatus.AppStatusMessages.*;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {
    private final RatingRepository ratingRepository;
    private final RatingMapper ratingMapper;
    private final TaskRepository taskRepository;
    private final StudentRepository studentRepository;

    @Override
    public ResponseDto<Page<RatingDto>> getAll(Integer size, Integer page) {
        Long count = ratingRepository.count();

        PageRequest pageRequest = PageRequest.of(
                (count / size) <= page ?
                        (count % size == 0 ? (int) (count / size) - 1
                                : (int) (count / size))
                        : page,
                size
        );
        try{
            Page<RatingDto> all = ratingRepository.findAll(pageRequest).map(ratingMapper::toDto);
            return ResponseDto.<Page<RatingDto>>builder()
                    .message(OK)
                    .code(OK_CODE)
                    .success(true)
                    .data(all)
                    .build();
        } catch (Exception e){
            return ResponseDto.<Page<RatingDto>>builder()
                    .code(DATABASE_ERROR_CODE)
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<RatingDto> getById(Integer id) {
        if (id == null){
            return ResponseDto.<RatingDto>builder()
                    .message("ID is NULL!")
                    .code(VALIDATION_ERROR_CODE)
                    .build();
        }

        try{
            Optional<Rating> byId = ratingRepository.findById(id);

            if (byId.isEmpty()){
                return ResponseDto.<RatingDto>builder()
                        .code(NOT_FOUND_ERROR_CODE)
                        .message(NOT_FOUND)
                        .build();
            }

            return ResponseDto.<RatingDto>builder()
                    .message(OK)
                    .code(OK_CODE)
                    .success(true)
                    .data(ratingMapper.toDto(byId.get()))
                    .build();
        } catch (Exception e){
            return ResponseDto.<RatingDto>builder()
                    .code(DATABASE_ERROR_CODE)
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<RatingDto> add(RatingDto ratingDto) {
        try{
            ratingRepository.save(ratingMapper.toEntity(ratingDto));
            return ResponseDto.<RatingDto>builder()
                    .message(OK)
                    .code(OK_CODE)
                    .success(true)
                    .data(ratingDto)
                    .build();
        } catch (Exception e){
            return ResponseDto.<RatingDto>builder()
                    .code(DATABASE_ERROR_CODE)
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<RatingDto> update(RatingDto ratingDto) {
        if (ratingDto.getId() == null){
            return ResponseDto.<RatingDto>builder()
                    .message("ID is NULL!")
                    .code(VALIDATION_ERROR_CODE)
                    .build();
        }

        try{
            Optional<Rating> byId = ratingRepository.findById(ratingDto.getId());

            if (byId.isEmpty()){
                return ResponseDto.<RatingDto>builder()
                        .code(NOT_FOUND_ERROR_CODE)
                        .message(NOT_FOUND)
                        .build();
            }

            Rating rating = byId.get();

            if (ratingDto.getDescription() != null){
                rating.setDescription(ratingDto.getDescription());
            }
            if (ratingDto.getTask() != null){
                rating.setTask(taskRepository.findById(ratingDto.getTask()).get());
            }
            if (ratingDto.getStudent() != null){
                rating.setStudent(studentRepository.findByIdAndIsActiveIsTrue(ratingDto.getStudent()).get());
            }

            ratingRepository.save(rating);

            return ResponseDto.<RatingDto>builder()
                    .message(OK)
                    .code(OK_CODE)
                    .success(true)
                    .data(ratingMapper.toDto(rating))
                    .build();
        } catch (Exception e){
            return ResponseDto.<RatingDto>builder()
                    .code(DATABASE_ERROR_CODE)
                    .message(DATABASE_ERROR + " : " + e.getMessage())
                    .build();
        }
    }
}
