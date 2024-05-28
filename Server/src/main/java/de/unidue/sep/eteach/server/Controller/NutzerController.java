package de.unidue.sep.eteach.server.Controller;

import de.unidue.sep.eteach.server.Entities.Adresse;
import de.unidue.sep.eteach.server.Entities.Nutzer;
import de.unidue.sep.eteach.server.Entities.Veranstaltung;
import de.unidue.sep.eteach.server.Service.NutzerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/eteach/nutzer")
public class NutzerController extends AppController{

    @Autowired
    NutzerService nutzerService;

    @GetMapping("/{id}")
    public ResponseEntity<Nutzer> getById(@PathVariable Integer id) {
        return new ResponseEntity<>(nutzerService.getNutzerById(id), HttpStatus.OK);
    }

    @GetMapping("/authenticate/{email}")
    public ResponseEntity<Nutzer> getNutzerByEmail(@PathVariable String email) {
        return new ResponseEntity<>(nutzerService.getNutzerByEmail(email), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Nutzer>> getAll() {
        return new ResponseEntity<>(nutzerService.getAll(), HttpStatus.OK);
    }

    @PostMapping(path = "/new", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Nutzer> createNutzer(@RequestBody Nutzer nutzer) {
        return new ResponseEntity<Nutzer>(nutzerService.createNutzer(nutzer), HttpStatus.CREATED);
    }
/*
    @PutMapping("/addFriend/{nutzer1Id}{nutzer2Id}")
    public ResponseEntity<List<Nutzer>> addFriend(@PathVariable Integer nutzer1Id, @PathVariable Integer nutzer2Id){
        return new ResponseEntity<>(nutzerService.addFriend(nutzer1Id, nutzer2Id), HttpStatus.OK);
    }

 */

    @PutMapping("/{id}")
    public ResponseEntity<Nutzer> editNutzer(
            @RequestBody Nutzer nutzer,
            @PathVariable String id) {
        return new ResponseEntity<>(nutzerService.editNutzer(nutzer), HttpStatus.OK);
    }

    @PutMapping("/{nutzerId}/adresse/{adresseId}")
    public ResponseEntity<Adresse> nutzeradresseAnlegen(
            @PathVariable Integer nutzerId,
            @PathVariable Integer adresseId
    ) {
        Nutzer nutzer = nutzerService.getNutzerById(nutzerId);
        Adresse adresse = adresseService.getAdresseById(adresseId);
        nutzer.nutzeradresseAnlegen(adresse);
        return new ResponseEntity<Adresse>(adresseService.createAdresse(adresse), HttpStatus.OK);
    }

    @PutMapping("/{nutzerId}/veranstaltungen/{veranstaltungId}")
    public ResponseEntity<Nutzer> nutzerInVeranstaltungEinschreiben(
            @PathVariable Integer nutzerId,
            @PathVariable Integer veranstaltungId
    ) {
        // Exception Handling to be added
        Nutzer nutzer = nutzerService.getNutzerById(nutzerId);
        Veranstaltung veranstaltung = veranstaltungService.getVeranstaltungById(veranstaltungId);
        nutzer.nutzerInVeranstaltungEinschreiben(veranstaltung);
        return new ResponseEntity<>(nutzerService.editNutzer(nutzer), HttpStatus.OK);
    }

    @DeleteMapping("/{nutzerId}/veranstaltungen/{veranstaltungId}")
    public ResponseEntity<Nutzer> nutzerAusVeranstaltungEntfernen(
            @PathVariable Integer nutzerId,
            @PathVariable Integer veranstaltungId
    ) {
        // Exception Handling to be added
        Nutzer nutzer = nutzerService.getNutzerById(nutzerId);
        Veranstaltung veranstaltung = veranstaltungService.getVeranstaltungById(veranstaltungId);
        nutzer.nutzerAusVeranstaltungEntfernen(veranstaltung);
        return new ResponseEntity<>(nutzerService.editNutzer(nutzer), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Nutzer> deleteNutzer(@PathVariable Integer id) {
        return new ResponseEntity<>(nutzerService.deleteNutzer(id), HttpStatus.OK);
    }
}
