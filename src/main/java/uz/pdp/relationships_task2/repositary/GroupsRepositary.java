package uz.pdp.relationships_task2.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.relationships_task2.Entitiy.Groups;

public interface GroupsRepositary extends JpaRepository<Groups,Integer> {
}
