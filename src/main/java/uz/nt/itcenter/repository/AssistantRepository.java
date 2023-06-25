package uz.nt.itcenter.repository;

import uz.nt.itcenter.model.Assistant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface AssistantRepository extends JpaRepository<Assistant, Integer> {
    List<Assistant> findAllByIsActiveIsTrue();
}
