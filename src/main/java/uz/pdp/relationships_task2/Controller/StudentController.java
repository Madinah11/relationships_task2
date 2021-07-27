package uz.pdp.relationships_task2.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.pdp.relationships_task2.Entitiy.Groups;
import uz.pdp.relationships_task2.Entitiy.Student;
import uz.pdp.relationships_task2.payload.StudentDto;
import uz.pdp.relationships_task2.repositary.GroupsRepositary;
import uz.pdp.relationships_task2.repositary.StudentRepositary;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {
    @Autowired
    StudentRepositary studentRepositary;

    @Autowired
    GroupsRepositary groupsRepositary;

    @PostMapping("/student")
    public String getStudent(@RequestBody StudentDto studentDto){
        Optional<Groups> byId = groupsRepositary.findById(studentDto.getId());
        if (byId.isPresent()){
            Groups groups1 = byId.get();
            Student student=new Student(null,studentDto.getFirstName(),studentDto.getLastName(),groups1);
            studentRepositary.save(student);
            return "Student added";
        }
        return "Student not found";
    }

    @GetMapping("/student/{id}")
    public Student getStudentById(@PathVariable Integer id){
        Optional<Student> byId = studentRepositary.findById(id);
        if (byId.isPresent()){
            Student student = byId.get();
            return student;
        }
        return new Student();
    }

    @GetMapping("/student")
    public List<Student> getStudent(){
        List<Student> all = studentRepositary.findAll();
        return all;
    }

//    Universitet uchun
    @GetMapping("/getByUniverId/{universityId}")
    public Page<Student> getByUniverId(@PathVariable Integer universityId,@RequestParam int page ){
        Pageable pageable= PageRequest.of(page,10);
        Page<Student> studentsByGroupNumber_faculty_universityId = studentRepositary.findAllByGroupNumber_Faculty_UniversityId(universityId,pageable);
        return studentsByGroupNumber_faculty_universityId;
    }
//    Fakultet uchun
    @GetMapping("/getByFacultyId/{facultyId}")
    public Page<Student> getByFacultyId(@PathVariable Integer facultyId,@RequestParam int page ) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Student> studentsByGroupNumber_facultyId = studentRepositary.findAllByGroupNumber_FacultyId(facultyId, pageable);
        return studentsByGroupNumber_facultyId;
    }
//   Gruppa uchun
    @GetMapping("/getByGroupId/{groupId}")
    public List<Student> getByGroupId(@PathVariable Integer groupId){
        List<Student> allByGroupNumber = studentRepositary.findAllByGroupNumber(groupId);
        return allByGroupNumber;
    }


        @DeleteMapping("/student/{id}")
    public String deleteGroups(@PathVariable Integer id){
        studentRepositary.deleteById(id);
        return "Student deleted";
    }

    @PutMapping("/student/{id}")
    public String editGroups(@PathVariable Integer id,@RequestBody StudentDto studentDto ){
        Optional<Student> byId = studentRepositary.findById(id);
        if (byId.isPresent()){
            Student student= byId.get();
            Optional<Groups> byId1 = groupsRepositary.findById(studentDto.getId());
            if (byId1.isPresent()){
                Groups groups = byId1.get();
                student.setFirstName(studentDto.getFirstName());
                student.setLastName(studentDto.getLastName());
                student.setGroupNumber(groups);
                studentRepositary.save(student);
                return "Student edited";
            }
        }
        return "Student not found";
    }

}
