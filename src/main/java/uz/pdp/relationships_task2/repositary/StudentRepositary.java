package uz.pdp.relationships_task2.repositary;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.relationships_task2.Entitiy.Groups;
import uz.pdp.relationships_task2.Entitiy.Student;

import java.util.List;

public interface StudentRepositary extends JpaRepository<Student,Integer> {
    List<Student> findAllByGroupNumber(Integer groupNumber);

    Page<Student> findAllByGroupNumber_FacultyId(Integer groupNumber_faculty_id, Pageable pageable);

    Page<Student> findAllByGroupNumber_Faculty_UniversityId(Integer groupNumber_faculty_university_id, Pageable pageable);



}
