package de.unidue.sep.eteach.server.Controller;

import de.unidue.sep.eteach.server.Entities.Lehrender;
import de.unidue.sep.eteach.server.Entities.Nutzer;
import de.unidue.sep.eteach.server.Entities.Veranstaltung;
import de.unidue.sep.eteach.server.Entities.Veranstaltungstyp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/eteach/veranstaltung")
public class VeranstaltungController extends AppController {

    @GetMapping("/{id}")
    public ResponseEntity<Veranstaltung> getById(@PathVariable Integer id) {
        return new ResponseEntity<>(veranstaltungService.getVeranstaltungById(id), HttpStatus.OK);
    }

    @GetMapping("/perteilnehmer/{nutzerId}")
    public ResponseEntity<List<Veranstaltung>> getVeranstaltungenByTeilnehmer(
            @PathVariable Integer nutzerId
    ) {
        Nutzer nutzer = nutzerService.getNutzerById(nutzerId);
        return new ResponseEntity<>(veranstaltungService.getVeranstaltungenByTeilnehmer(nutzer), HttpStatus.OK);
    }

    @GetMapping("/pertitle/{title}")
    public ResponseEntity<Veranstaltung> getVeranstaltungByTitle(@PathVariable String title) {
        return new ResponseEntity<>(veranstaltungService.getVeranstaltungByTitle(title), HttpStatus.OK);
    }

    //Meins --> Können spezifisch nach Projektgruppen suchen
    @GetMapping("/searchProjektgruppe/{title}")
    public ResponseEntity<List<Veranstaltung>> searchProjektgruppeByTitel(@PathVariable String title){

        return new ResponseEntity<>(veranstaltungService.searchProjektgruppeByTitel(title), HttpStatus.OK);
    }

    //Meins --> Können nach einem Veranstaltungstypen suchen
    @GetMapping("/all/{veranstaltungsTyp}")
    public ResponseEntity<List<Veranstaltung>> getAllByVeranstaltungstyp(@PathVariable String veranstaltungsTyp){

        return new ResponseEntity<>(
                veranstaltungService.getVeranstaltungByVeranstaltungstyp(Veranstaltungstyp.valueOf(veranstaltungsTyp.toUpperCase()))
                        ,HttpStatus.OK);

    }


    @GetMapping("/all")
    public ResponseEntity<List<Veranstaltung>> getAll() {
        return new ResponseEntity<>(veranstaltungService.getAll(), HttpStatus.OK);
    }

    @PostMapping(path = "/new", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Veranstaltung> createVeranstaltung(@RequestBody Veranstaltung veranstaltung) {
        return new ResponseEntity<>(veranstaltungService.createVeranstaltung(veranstaltung), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Veranstaltung> editveranstaltung(@RequestBody Veranstaltung veranstaltung) {
        return new ResponseEntity<>(veranstaltungService.editVeranstaltung(veranstaltung), HttpStatus.OK);
    }

    @PutMapping("/{veranstaltungId}/organisator/{lehrenderId}")
    public ResponseEntity<Veranstaltung> veranstaltungsorganisatorFestlegen(
            @PathVariable Integer veranstaltungId,
            @PathVariable Integer lehrenderId
    ) {
        // Exception Handling to be added
        Veranstaltung veranstaltung = veranstaltungService.getVeranstaltungById(veranstaltungId);
        Lehrender lehrender = lehrenderService.getLehrenderById(lehrenderId);
        veranstaltung.veranstaltungsorganisatorFestlegen(lehrender);
        return new ResponseEntity<>(veranstaltungService.createVeranstaltung(veranstaltung), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Veranstaltung> deleteveranstaltung(@PathVariable Integer id) {
        return new ResponseEntity<>(veranstaltungService.deleteVeranstaltung(id), HttpStatus.OK);
    }
}
