package de.unidue.sep.eteach.server.Controller;

import de.unidue.sep.eteach.server.Entities.Adresse;
import de.unidue.sep.eteach.server.Service.AdresseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/eteach/adresse")
public class AdresseController extends AppController{

    @Autowired
    AdresseService adresseService;

    @GetMapping("/{id}")
    public ResponseEntity<Adresse> getById(@PathVariable Integer id) {
        return new ResponseEntity<>(adresseService.getAdresseById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Adresse>> getAll() {
        return new ResponseEntity<>(adresseService.getAll(), HttpStatus.OK);
    }

    @PostMapping(path = "/new", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Adresse> createNewAdresse(@RequestBody Adresse adresse) {
        return new ResponseEntity<Adresse>(adresseService.createAdresse(adresse), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Adresse> editAdresse(
            @RequestBody Adresse adresse,
            @PathVariable String id) {
        return new ResponseEntity<>(adresseService.editAdresse(adresse), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Adresse> deleteAdresse(
            @PathVariable Integer id) {
        return new ResponseEntity<>(adresseService.deleteAdresse(id), HttpStatus.OK);
    }
}
