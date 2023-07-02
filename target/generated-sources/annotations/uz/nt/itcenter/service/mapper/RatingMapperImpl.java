package uz.nt.itcenter.service.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import uz.nt.itcenter.dto.RatingDto;
import uz.nt.itcenter.model.Rating;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-02T11:03:31+0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.7 (Amazon.com Inc.)"
)
@Component
public class RatingMapperImpl extends RatingMapper {

    @Override
    public RatingDto toDto(Rating rating) {
        if ( rating == null ) {
            return null;
        }

        RatingDto ratingDto = new RatingDto();

        return ratingDto;
    }

    @Override
    public Rating toEntity(RatingDto ratingDto) {
        if ( ratingDto == null ) {
            return null;
        }

        Rating rating = new Rating();

        return rating;
    }
}
