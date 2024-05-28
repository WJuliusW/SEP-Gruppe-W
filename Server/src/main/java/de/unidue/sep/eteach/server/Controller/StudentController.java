package de.unidue.sep.eteach.server.Controller;

import de.unidue.sep.eteach.server.Entities.Student;
import de.unidue.sep.eteach.server.Service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/eteach/student")
public class StudentController extends AppController{

    @Autowired
    StudentService studentService;

    @GetMapping("/{id}")
    public ResponseEntity<Student> getById(@PathVariable Integer id) {
        return new ResponseEntity<>(studentService.getStudentById(id), HttpStatus.OK);
    }

    @GetMapping("/authenticate/{matrikelnummer}")
    public ResponseEntity<Student> getByMatrikelnummer(@PathVariable Integer matrikelnummer) {
        return new ResponseEntity<>(studentService.getStudentByMatrikelnummer(matrikelnummer), HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Student> getStudentByEmail(@PathVariable String email) {
        return new ResponseEntity<>(studentService.getStudentByEmail(email), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Student>> getAll() {
        return new ResponseEntity<>(studentService.getAll(), HttpStatus.OK);
    }

    @PostMapping(path = "/new", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        return new ResponseEntity<Student>(studentService.createStudent(student), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> editStudent(
            @RequestBody Student student,
            @PathVariable String id
    ) {
        return new ResponseEntity<>(studentService.editStudent(student), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Integer id) {
        return new ResponseEntity<>(studentService.deleteStudent(id), HttpStatus.OK);
    }
}
