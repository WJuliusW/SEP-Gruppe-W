package de.unidue.sep.eteach.server.Controller;

import de.unidue.sep.eteach.server.Entities.Abgabe;
import de.unidue.sep.eteach.server.Entities.Quiz;
import de.unidue.sep.eteach.server.Entities.Veranstaltung;
import de.unidue.sep.eteach.server.Service.AbgabeService;
import de.unidue.sep.eteach.server.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/eteach/abgabe")
public class AbgabeController extends AppController{

    @Autowired
    AbgabeService abgabeService;

    @GetMapping("/{id}")
    public ResponseEntity<Abgabe> getById(@PathVariable Integer id) {
        return new ResponseEntity<>(abgabeService.getAbgabeById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Abgabe>> getAll() {
        return new ResponseEntity<>(abgabeService.getAll(), HttpStatus.OK);
    }

    @PostMapping(path = "/new", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Abgabe> createNewAbgabe(@RequestBody Abgabe abgabe) {
        return new ResponseEntity<Abgabe>(abgabeService.createAbgabe(abgabe), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Abgabe> editAbgabe(
            @RequestBody Abgabe abgabe,
            @PathVariable Integer id) {
        return new ResponseEntity<>(abgabeService.editAbgabe(abgabe), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Abgabe> deleteAbgabe(
            @PathVariable Integer id) {
        return new ResponseEntity<>(abgabeService.deleteAbgabe(id), HttpStatus.OK);
    }



}
