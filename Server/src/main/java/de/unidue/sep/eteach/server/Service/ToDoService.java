package de.unidue.sep.eteach.server.Service;

import de.unidue.sep.eteach.server.Entities.Adresse;
import de.unidue.sep.eteach.server.Entities.ToDo;
import de.unidue.sep.eteach.server.Entities.Veranstaltung;
import de.unidue.sep.eteach.server.Repositories.AdresseRepo;
import de.unidue.sep.eteach.server.Repositories.ToDoRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ToDoService {

    ToDoRepo toDoRepo;

    @Autowired
    public ToDoService(ToDoRepo toDoRepo) {
        this.toDoRepo = toDoRepo;
    }

    public ToDo getToDoById(Integer id) {return toDoRepo.findToDoById(id);}

    public ToDo createToDo(ToDo toDo) {
        return toDoRepo.save(toDo);
    }

    public List<ToDo> getAll() {
        return toDoRepo.findAll();
    }

    //Meins
    public List<ToDo> getToDosByVeranstaltug (Veranstaltung veranstaltung){

        return toDoRepo.findToDosByVeranstaltung(veranstaltung);
    }

    public ToDo editToDo(ToDo toDoEditiert){
        return toDoRepo.save(toDoEditiert);
    }

    public ToDo deleteToDo(Integer id){
        ToDo toDo=toDoRepo.findToDoById(id);
        toDoRepo.delete(toDo);
        return toDo;
    }

}
