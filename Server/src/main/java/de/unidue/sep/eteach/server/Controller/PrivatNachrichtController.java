package de.unidue.sep.eteach.server.Controller;

import de.unidue.sep.eteach.server.Entities.Abgabe;
import de.unidue.sep.eteach.server.Entities.Nutzer;
import de.unidue.sep.eteach.server.Entities.PrivatNachricht;
import de.unidue.sep.eteach.server.Entities.PrivatNachrichtTyp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/eteach/privatChat")
public class PrivatNachrichtController extends AppController {

    @GetMapping("/nachrichten/{empfaengerId}")
    public ResponseEntity<List<PrivatNachricht>> getPrivatNachrichtenByEmpfaenger(@PathVariable Integer empfaengerId) {
        return new ResponseEntity<>(privatNachrichtService.getPrivatNachrichtenByEmpfaenger(nutzerService.getNutzerById(empfaengerId)), HttpStatus.OK);
    }

    @GetMapping("/freundschaftsanfragen/{empfaengerId}")
    public ResponseEntity<List<PrivatNachricht>> getFreunschaftsanfragenByEmpfaenger(@PathVariable Integer empfaengerId) {
        return new ResponseEntity<>(privatNachrichtService.getFreundschaftsanfragenByEmpfaenger(nutzerService.getNutzerById(empfaengerId)), HttpStatus.OK);
    }

    @GetMapping("/nachrichten/{absenderId}/{empfaengerId}")
    public ResponseEntity<List<PrivatNachricht>> getPrivatNachrichtenByAbsenderOrEmpfaenger(@PathVariable Integer absenderId, @PathVariable Integer empfaengerId) {
        return new ResponseEntity<>(privatNachrichtService.getPrivatNachrichtenByAbsenderOrEmpfaenger(nutzerService.getNutzerById(absenderId), nutzerService.getNutzerById(empfaengerId)), HttpStatus.OK);
    }

    @PostMapping(path = "/nachrichten/{absenderId}/{empfaengerId}/new", produces = "application/json", consumes = "application/json")
    public ResponseEntity<PrivatNachricht> createPrivatNachricht(@PathVariable Integer absenderId, @PathVariable Integer empfaengerId, @RequestBody PrivatNachricht privatNachricht) {
        return new ResponseEntity<>(privatNachrichtService.createPrivatNachricht(create(absenderId, empfaengerId, privatNachricht, PrivatNachrichtTyp.NACHRICHT)), HttpStatus.CREATED);
    }

    @PostMapping(path = "/freundschaftsanfragen/{absenderId}/{empfaengerId}/new", produces = "application/json", consumes = "application/json")
    public ResponseEntity<PrivatNachricht> createFreundschaftsanfrage(@PathVariable Integer absenderId, @PathVariable Integer empfaengerId, @RequestBody PrivatNachricht privatNachricht) {
        return new ResponseEntity<>(privatNachrichtService.createPrivatNachricht(create(absenderId, empfaengerId, privatNachricht, PrivatNachrichtTyp.FREUNDSCHAFTSANFRAGE)), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PrivatNachricht> deletePrivatNachricht(@PathVariable Integer id) {
        return new ResponseEntity<>(privatNachrichtService.deletePrivatNachricht(id), HttpStatus.OK);
    }

    private PrivatNachricht create(Integer absenderId, Integer empfaengerId, PrivatNachricht privatNachricht, PrivatNachrichtTyp privatNachrichtTyp) {
        Nutzer absender = nutzerService.getNutzerById(absenderId);
        Nutzer empfaenger = nutzerService.getNutzerById(empfaengerId);
        privatNachricht.setAbsender(absender);
        privatNachricht.setEmpfaenger(empfaenger);
        privatNachricht.setTyp(privatNachrichtTyp);
        if (privatNachricht.getZeitstempel() == null) {
            privatNachricht.setZeitstempel(new Date());
        }

        return privatNachricht;
    }

    //Julius
    @GetMapping("/all")
    public ResponseEntity<List<PrivatNachricht>> getAll() {
        return new ResponseEntity<>(privatNachrichtService.getAll(), HttpStatus.OK);
    }

}