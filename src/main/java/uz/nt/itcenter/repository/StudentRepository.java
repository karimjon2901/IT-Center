package uz.nt.itcenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.nt.itcenter.dto.StudentDto;
import uz.nt.itcenter.model.Student;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> findAllByIsActiveIsTrue();
    List<StudentDto> findAllByGroupIdAndIsActiveIsTrue(Integer group_id);

    Optional<Student> findByIdAndIsActiveIsTrue(Integer integer);
}
