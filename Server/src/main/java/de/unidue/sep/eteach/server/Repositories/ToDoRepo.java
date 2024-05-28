package de.unidue.sep.eteach.server.Repositories;

import de.unidue.sep.eteach.server.Entities.Adresse;
import de.unidue.sep.eteach.server.Entities.ToDo;
import de.unidue.sep.eteach.server.Entities.Veranstaltung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoRepo extends JpaRepository<ToDo, Integer>  {
    ToDo findToDoById(Integer id);
    List<ToDo> findAll();

    //Man möchte auch nur die To`do es für eine Veranstaltung kriegen
    List <ToDo> findToDosByVeranstaltung(Veranstaltung veranstaltung);
}

