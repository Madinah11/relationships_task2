package uz.pdp.relationships_task2.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.relationships_task2.Entitiy.Address;
import uz.pdp.relationships_task2.Entitiy.District;
import uz.pdp.relationships_task2.Entitiy.University;
import uz.pdp.relationships_task2.payload.AddressDto;
import uz.pdp.relationships_task2.payload.UniversityDto;
import uz.pdp.relationships_task2.repositary.AddressRepositary;
import uz.pdp.relationships_task2.repositary.UniversityRepositary;

import java.util.List;
import java.util.Optional;

@RestController
public class UniversityController {
    @Autowired
    UniversityRepositary universityRepositary;
    @Autowired
    AddressRepositary addressRepositary;

    @PostMapping("/university")
    public String getUniversity(@RequestBody UniversityDto universityDto){
        Optional<Address> byId = addressRepositary.findById(universityDto.getId());
        if (byId.isPresent()){
            Address address = byId.get();
            University university=new University(null,universityDto.getName(),address);
            universityRepositary.save(university);
            return "University added";
        }
        return "University not found";
    }

    @GetMapping("/university/{id}")
    public University getuniversityById(@PathVariable Integer id){
        Optional<University> byId = universityRepositary.findById(id);
        if (byId.isPresent()){
            University university = byId.get();
            return university;
        }
        return new University();
    }

    @GetMapping("/university")
    public List<University> getUniversity(){
        List<University> all = universityRepositary.findAll();
        return all;
    }

    @DeleteMapping("/university/{id}")
    public String deleteUniversity(@PathVariable Integer id){
        universityRepositary.deleteById(id);
        return "University deleted";
    }

    @PutMapping("/university/{id}")
    public String editUniversity(@PathVariable Integer id,@RequestBody UniversityDto universityDto){
        Optional<University> byId = universityRepositary.findById(id);
        if (byId.isPresent()){
            University university = byId.get();
            Optional<Address> byId1 = addressRepositary.findById(universityDto.getId());
            if (byId1.isPresent()){
                Address address = byId1.get();
                university.setName(universityDto.getName());
                university.setAddress(address);
                universityRepositary.save(university);
                return "University edited";
            }
        }
        return "University not found";
    }
}
