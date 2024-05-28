package de.unidue.sep.eteach.server.Repositories;

import de.unidue.sep.eteach.server.Entities.Abgabe;
import de.unidue.sep.eteach.server.Entities.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AbgabeRepo extends JpaRepository<Abgabe, Integer>  {
    Abgabe findAbgabeById(Integer id);
    List<Abgabe> findAll();
}

