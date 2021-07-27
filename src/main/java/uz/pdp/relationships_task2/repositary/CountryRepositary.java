package uz.pdp.relationships_task2.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.relationships_task2.Entitiy.Country;

public interface CountryRepositary extends JpaRepository<Country,Integer> {
}
