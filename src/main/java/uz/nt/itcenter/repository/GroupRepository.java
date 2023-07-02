package uz.nt.itcenter.repository;

import uz.nt.itcenter.model.Groups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Groups, Integer> {
    List<Groups> findAllByIsActiveIsTrue();

    Optional<Groups> findByIdAndIsActiveIsTrue(Integer id);
    List<Groups> findAllByIsActiveIsTrueAndAssistantId(Integer id);
}
