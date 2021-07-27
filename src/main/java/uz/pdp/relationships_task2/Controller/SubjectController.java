package uz.pdp.relationships_task2.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.relationships_task2.Entitiy.Subject;
import uz.pdp.relationships_task2.Entitiy.Teacher;
import uz.pdp.relationships_task2.repositary.SubjectRepositary;

import java.util.List;
import java.util.Optional;

@RestController
public class SubjectController {
    @Autowired
    SubjectRepositary subjectRepositary;

    @PostMapping("subject")
    public String addSubject(@RequestBody Subject subject){
        Subject subject1=new Subject(null,subject.getName());
        subjectRepositary.save(subject1);
        return "Subject added";

    }

    @GetMapping("subject/{id}")
    public Subject getSubjectById(@PathVariable Integer id){
        Optional<Subject> byId = subjectRepositary.findById(id);
        if (byId.isPresent()){
            Subject subject = byId.get();
            return subject;
        }
        return new Subject();
    }

   @GetMapping("subject")
    public List<Subject> getAllSubject(){
        List<Subject> all = subjectRepositary.findAll();
        return all;

    }

    @DeleteMapping("subject/{id}")
    public String deleteSubject(@PathVariable Integer id){
        subjectRepositary.deleteById(id);
        return "Subject deleted";
    }

    @PutMapping("subject/{id}")
    public String editContinent(@PathVariable Integer id,@RequestBody Subject subject){
        Optional<Subject> byId = subjectRepositary.findById(id);
        if (byId.isPresent()){
            Subject subject1 = byId.get();
            subject1.setName(subject.getName());
            subjectRepositary.save(subject1);
            return "Subject edited";
        }
        return "Subject not found";
    }
}
