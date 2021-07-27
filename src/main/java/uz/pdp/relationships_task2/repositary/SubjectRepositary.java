package uz.pdp.relationships_task2.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.relationships_task2.Entitiy.Subject;



public interface SubjectRepositary extends JpaRepository<Subject, Integer> {
}
