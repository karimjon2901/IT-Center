package uz.nt.itcenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.nt.itcenter.model.Task;
@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

}
