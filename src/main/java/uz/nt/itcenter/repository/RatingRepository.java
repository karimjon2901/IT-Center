package uz.nt.itcenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.nt.itcenter.model.Rating;
@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {
}
