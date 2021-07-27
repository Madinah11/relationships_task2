package uz.pdp.relationships_task2.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.relationships_task2.Entitiy.Country;
import uz.pdp.relationships_task2.Entitiy.Region;

public interface RegionRepositary extends JpaRepository<Region,Integer> {
}
