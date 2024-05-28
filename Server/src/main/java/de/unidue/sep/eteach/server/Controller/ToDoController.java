package de.unidue.sep.eteach.server.Controller;

import de.unidue.sep.eteach.server.Entities.Adresse;
import de.unidue.sep.eteach.server.Entities.Nutzer;
import de.unidue.sep.eteach.server.Entities.ToDo;
import de.unidue.sep.eteach.server.Service.AdresseService;
import de.unidue.sep.eteach.server.Service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/eteach/todo")
public class ToDoController extends AppController{

    @Autowired
    ToDoService todoService;

    @GetMapping("/{id}")
    public ResponseEntity<ToDo> getById(@PathVariable Integer id) {
        return new ResponseEntity<>(todoService.getToDoById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ToDo>> getAll() {
        return new ResponseEntity<>(todoService.getAll(), HttpStatus.OK);
    }

    //Meins
    // Bekommen alle To`does der Veranstaltung
    @GetMapping("/{veranstaltungId}/all")
    public ResponseEntity<List<ToDo>> getAllByVeranstaltung(@PathVariable Integer veranstaltungId) {
        return new ResponseEntity<>(todoService.getToDosByVeranstaltug(veranstaltungService.getVeranstaltungById(veranstaltungId)), HttpStatus.OK);
    }
    // Anpassung
    // Insofern das man das To Do der Projekgruppe zuordnen kann und sogar dem Nutzer direkt
    @PostMapping(path = "/{veranstaltungId}/{nutzerId}/new", produces = "application/json", consumes = "application/json")
    public ResponseEntity<ToDo> createNewToDo(@PathVariable Integer veranstaltungId, @PathVariable Integer nutzerId, @RequestBody ToDo todo ) {

        todo.setVeranstaltung(veranstaltungService.getVeranstaltungById(veranstaltungId));
        todo.setAufgabensteller(nutzerService.getNutzerById(nutzerId));

        return new ResponseEntity<>(todoService.createToDo(todo), HttpStatus.CREATED);

    }

    @PostMapping(path = "/new", produces = "application/json", consumes = "application/json")
    public ResponseEntity<ToDo> createNutzer(@RequestBody ToDo todo) {
        return new ResponseEntity<>(todoService.createToDo(todo), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ToDo> editToDo(
            @RequestBody ToDo todo,
            @PathVariable String id) {
        return new ResponseEntity<>(todoService.editToDo(todo), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ToDo> deleteToDo(
            @PathVariable Integer id) {
        return new ResponseEntity<>(todoService.deleteToDo(id), HttpStatus.OK);
    }
}
