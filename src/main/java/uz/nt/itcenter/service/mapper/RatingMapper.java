package uz.nt.itcenter.service.mapper;

import org.mapstruct.Mapper;
import uz.nt.itcenter.dto.RatingDto;
import uz.nt.itcenter.model.Rating;

@Mapper(componentModel = "spring")
public abstract class RatingMapper implements CommonMapper<RatingDto, Rating>{
    @Override
    public abstract RatingDto toDto(Rating rating);

    @Override
    public abstract Rating toEntity(RatingDto ratingDto);
}
