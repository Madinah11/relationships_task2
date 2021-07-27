package uz.pdp.relationships_task2.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.relationships_task2.Entitiy.Faculty;
import uz.pdp.relationships_task2.Entitiy.University;
import uz.pdp.relationships_task2.payload.FacultyDto;
import uz.pdp.relationships_task2.repositary.FacultyRepositary;
import uz.pdp.relationships_task2.repositary.UniversityRepositary;

import java.util.List;
import java.util.Optional;

@RestController
public class FacultyController {
    @Autowired
    FacultyRepositary facultyRepositary;
    @Autowired
    UniversityRepositary universityRepositary;

    @PostMapping("/faculty")
    public String getFaculty(@RequestBody FacultyDto facultyDto){
        Optional<University> byId = universityRepositary.findById(facultyDto.getId());
        if (byId.isPresent()){
            University university1 = byId.get();
            Faculty faculty=new Faculty(null,facultyDto.getName(),university1);
            facultyRepositary.save(faculty);
            return "Faculty added";
        }
        return "Faculty not found";
    }

    @GetMapping("/faculty/{id}")
    public Faculty getFacultyById(@PathVariable Integer id){
        Optional<Faculty> byId = facultyRepositary.findById(id);
        if (byId.isPresent()){
            Faculty faculty = byId.get();
            return faculty;
        }
        return new Faculty();
    }

    @GetMapping("/faculty")
    public List<Faculty> getFaculty(){
        List<Faculty> all = facultyRepositary.findAll();
        return all;
    }

    @DeleteMapping("/faculty/{id}")
    public String deleteFaculty(@PathVariable Integer id){
        facultyRepositary.deleteById(id);
        return "Faculty deleted";
    }

    @PutMapping("/faculty/{id}")
    public String editfaculty(@PathVariable Integer id,@RequestBody FacultyDto facultyDto){
        Optional<Faculty> byId = facultyRepositary.findById(id);
        if (byId.isPresent()){
            Faculty faculty = byId.get();
            Optional<University> byId1 = universityRepositary.findById(facultyDto.getId());
            if (byId1.isPresent()){
                University university = byId1.get();
               faculty.setName(facultyDto.getName());
               faculty.setUniversity(university);
                facultyRepositary.save(faculty);
                return "Faculty edited";
            }
        }
        return "Faculty not found";
    }

}
