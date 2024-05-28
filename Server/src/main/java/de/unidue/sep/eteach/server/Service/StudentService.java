package de.unidue.sep.eteach.server.Service;

import de.unidue.sep.eteach.server.Entities.Student;
import de.unidue.sep.eteach.server.Repositories.StudentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    StudentRepo studentRepo;

    @Autowired
    public StudentService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }


    public Student getStudentByMatrikelnummer(Integer matrikelnummer) {
        return studentRepo.getStudentByMatrikelnummer(matrikelnummer);
    }

    public Student getStudentById(Integer id) {
        return studentRepo.findStudentById(id);
    }

    public Student getStudentByEmail(String email) {
        return studentRepo.findStudentByEmail(email);
    }

    public Student createStudent(Student student) {
        return studentRepo.save(student);
    }

    public List<Student> getAll() {
        return studentRepo.findAll();
    }

    public Student editStudent(Student studentEditiert) {
        return studentRepo.save(studentEditiert);
    }

    public Student deleteStudent(Integer id) {
        Student student = studentRepo.findStudentById(id);
        studentRepo.delete(student);
        return student;
    }

    public int getNumberStudents() {
        return getNumberStudents();
    }
}
