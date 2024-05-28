package de.unidue.sep.eteach.server.Repositories;

import de.unidue.sep.eteach.server.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer>  {
    Student findStudentById(Integer id);
    List<Student> findAll();
    Student getStudentByMatrikelnummer(Integer matrikelnummer);
    Student findStudentByEmail(String email);
}

