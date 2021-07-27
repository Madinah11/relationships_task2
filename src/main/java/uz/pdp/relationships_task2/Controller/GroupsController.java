package uz.pdp.relationships_task2.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.relationships_task2.Entitiy.Faculty;
import uz.pdp.relationships_task2.Entitiy.Groups;
import uz.pdp.relationships_task2.Entitiy.University;
import uz.pdp.relationships_task2.payload.FacultyDto;
import uz.pdp.relationships_task2.payload.GroupsDto;
import uz.pdp.relationships_task2.repositary.FacultyRepositary;
import uz.pdp.relationships_task2.repositary.GroupsRepositary;

import java.util.List;
import java.util.Optional;

@RestController
public class GroupsController {
    @Autowired
    FacultyRepositary facultyRepositary;
    @Autowired
    GroupsRepositary groupsRepositary;

    @PostMapping("/groups")
    public String getGroups(@RequestBody GroupsDto groupsDto){
        Optional<Faculty> byId = facultyRepositary.findById(groupsDto.getId());
        if (byId.isPresent()){
            Faculty faculty1 = byId.get();
            Groups groups=new Groups(null,groupsDto.getNumber(),faculty1);
            groupsRepositary.save(groups);
            return "Groups added";
        }
        return "Groups not found";
    }

    @GetMapping("/groups/{id}")
    public Groups getGroupById(@PathVariable Integer id){
        Optional<Groups> byId = groupsRepositary.findById(id);
        if (byId.isPresent()){
            Groups groups = byId.get();            
            return groups;
        }
        return new Groups();
    }

    @GetMapping("/groups")
    public List<Groups> getGroups(){
        List<Groups> all = groupsRepositary.findAll();
        return all;
    }

    @DeleteMapping("/groups/{id}")
    public String deleteGroups(@PathVariable Integer id){
        groupsRepositary.deleteById(id);
        return "Groups deleted";
    }

    @PutMapping("/Groups/{id}")
    public String editGroups(@PathVariable Integer id,@RequestBody GroupsDto groupsDto){
        Optional<Groups> byId = groupsRepositary.findById(id);
        if (byId.isPresent()){
            Groups groups = byId.get();
            Optional<Faculty> byId1 = facultyRepositary.findById(groupsDto.getId());
            if (byId1.isPresent()){
                Faculty faculty = byId1.get();
                groups.setNumber(groupsDto.getNumber());
                groups.setFaculty(faculty);
                groupsRepositary.save(groups);
                return "Groups edited";
            }
        }
        return "Groups not found";
    }

}
