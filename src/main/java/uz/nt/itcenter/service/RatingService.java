package uz.nt.itcenter.service;

import org.springframework.data.domain.Page;
import uz.nt.itcenter.dto.RatingDto;
import uz.nt.itcenter.dto.ResponseDto;

public interface RatingService {
    ResponseDto<Page<RatingDto>> getAll(Integer size, Integer page);

    ResponseDto<RatingDto> getById(Integer id);

    ResponseDto<RatingDto> add(RatingDto ratingDto);

    ResponseDto<RatingDto> update(RatingDto ratingDto);
}
