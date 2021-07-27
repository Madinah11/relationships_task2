package uz.pdp.relationships_task2.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.relationships_task2.Entitiy.Teacher;
import uz.pdp.relationships_task2.repositary.TeacherRepositary;

import java.util.List;
import java.util.Optional;

@RestController
public class TeachrController {
    @Autowired
    TeacherRepositary teacherRepositary;

    @PostMapping("/teacher")
    public String addTeacher(@RequestBody Teacher teacher){
        Teacher teacher1=new Teacher(null,teacher.getFirstName(),teacher.getLastName());
        teacherRepositary.save(teacher1);
        return "Teacher added";

    }

    @GetMapping("/teacher/{id}")
    public Teacher getTeacherById(@PathVariable Integer id){
        Optional<Teacher> byId = teacherRepositary.findById(id);
        if (byId.isPresent()){
            Teacher teacher = byId.get();
            return teacher;
        }
        return new Teacher();
    }

    @GetMapping("/teacher")
    public List<Teacher> getAllTeachers(){
        List<Teacher> all = teacherRepositary.findAll();
        return all;

    }

    @DeleteMapping("/teacher/{id}")
    public String deleteteacher(@PathVariable Integer id){
        teacherRepositary.deleteById(id);
        return "teacher deleted";
    }

    @PutMapping("/teacher/{id}")
    public String editTeacher(@PathVariable Integer id,@RequestBody Teacher teacher){
        Optional<Teacher> byId = teacherRepositary.findById(id);
        if (byId.isPresent()){
            Teacher teacher1 = byId.get();
            teacher1.setFirstName(teacher.getFirstName());
            teacher1.setLastName(teacher.getLastName());
            teacherRepositary.save(teacher1);
            return "Teacher edited";
        }
        return "Teacher not found";
    }
}
