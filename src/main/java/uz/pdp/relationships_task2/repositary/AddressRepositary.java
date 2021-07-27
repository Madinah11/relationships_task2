package uz.pdp.relationships_task2.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.relationships_task2.Entitiy.Address;

public interface AddressRepositary extends JpaRepository<Address,Integer> {
}
