package de.unidue.sep.eteach.server.Controller;

import de.unidue.sep.eteach.server.Entities.Freundschaft;
import de.unidue.sep.eteach.server.Service.FreundschaftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/eteach/freundschaft")
public class FreundschaftController extends AppController{

    @Autowired
    FreundschaftService freundschaftService;

    @GetMapping("/{id}")
    public ResponseEntity<Freundschaft> getById(@PathVariable Integer id) {
        return new ResponseEntity<>(freundschaftService.getFreundschaftById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Freundschaft>> getAll() {
        return new ResponseEntity<>(freundschaftService.getAll(), HttpStatus.OK);
    }

    @PostMapping(path = "/new", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Freundschaft> createNewFreundschaft(@RequestBody Freundschaft freundschaft) {
        return new ResponseEntity<Freundschaft>(freundschaftService.createFreundschaft(freundschaft), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Freundschaft> editFreundschaft(
            @RequestBody Freundschaft freundschaft,
            @PathVariable Integer id) {
        return new ResponseEntity<>(freundschaftService.editFreundschaft(freundschaft), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Freundschaft> deleteFreundschaft(
            @PathVariable Integer id) {
        return new ResponseEntity<>(freundschaftService.deleteFreundschaft(id), HttpStatus.OK);
    }



}
