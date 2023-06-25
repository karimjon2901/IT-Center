package uz.nt.itcenter.repository;

import org.aspectj.apache.bcel.classfile.Module;
import uz.nt.itcenter.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> findAllByIsActiveIsTrue();
}
