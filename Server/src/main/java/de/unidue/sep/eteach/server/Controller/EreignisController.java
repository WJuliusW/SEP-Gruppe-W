package de.unidue.sep.eteach.server.Controller;

import de.unidue.sep.eteach.server.Entities.Ereignis;
import de.unidue.sep.eteach.server.Entities.Veranstaltung;
import de.unidue.sep.eteach.server.Service.EreignisService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/eteach/ereignis")
public class EreignisController extends AppController{

    @Autowired
    EreignisService ereignisService;

    @Autowired
    public EreignisController(EreignisService ereignisService) {
        this.ereignisService = ereignisService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ereignis> getById(@PathVariable Integer id) {
        return new ResponseEntity<>(ereignisService.getEreignisById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Ereignis>> getAll() {
        return new ResponseEntity<>(ereignisService.getAll(), HttpStatus.OK);
    }

    //meins
    @GetMapping("/nutzer/{nutzerId}/all")
    public ResponseEntity<List<Ereignis>> getAllEreignisByNutzer(@PathVariable Integer nutzerId){
        return new ResponseEntity<>(ereignisService.getAllEreignisByNutzer(nutzerId), HttpStatus.OK);
    }

    @GetMapping("/veranstaltung/{veranstaltungId}/all")
    public ResponseEntity<List<Ereignis>> getAllEreignisByVeranstaltung(@PathVariable Integer veranstaltungId){
        return new ResponseEntity<>(ereignisService.getAllEreignisByVeranstaltung(veranstaltungId), HttpStatus.OK);
    }

    @GetMapping("/kalender/{nutzerId}")
    public ResponseEntity<List<Ereignis>> getKalenderForNutzer(@PathVariable Integer nutzerId){
        return new ResponseEntity<>(ereignisService.getKalenderForNutzer(nutzerId), HttpStatus.OK);
    }

    @GetMapping("/kalender/{nutzerId}/{day}")
    public ResponseEntity<List<Ereignis>> getTagesKalenderForNutzer(@PathVariable Integer nutzerId, @PathVariable String day){
        return new ResponseEntity<>(ereignisService.getKalenderForNutzerForDay(nutzerId, day), HttpStatus.OK);
    }
    //bis hierhin


    //Neu
    @GetMapping ("/reminder/{nutzerId}")
    public  ResponseEntity<List<Ereignis>> getReminderForNutzer (@PathVariable Integer nutzerId) throws ParseException{

        return new ResponseEntity<>(ereignisService.getReminderEreignisseByNutzer(nutzerId), HttpStatus.OK);
    }

    @PostMapping ("/reminder/{nutzerId}/date")
    public  ResponseEntity<List<Ereignis>> getReminderForNutzer (@PathVariable Integer nutzerId, @RequestBody String s) throws ParseException{
        DateFormat dateFormatEreignis = new SimpleDateFormat("dd.MM.yyyy, HH:mm:ss", Locale.ENGLISH);
        Date date =dateFormatEreignis.parse(s.replaceAll("\"",""));
        return new ResponseEntity<>(ereignisService.getReminderEreignisseByNutzer(nutzerId, date), HttpStatus.OK);
    }

    @PostMapping(path = "/new", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Ereignis> createNewEreignis(@RequestBody Ereignis ereignis) {
        return new ResponseEntity<Ereignis>(ereignisService.createEreignis(ereignis), HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Ereignis> editEreignis(
            @RequestBody Ereignis ereignis,
            @PathVariable String id) {
        return new ResponseEntity<>(ereignisService.editEreignis(ereignis), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Ereignis> deleteEreignis(@PathVariable Integer id) {
        return new ResponseEntity<>(ereignisService.deleteEreignis(id), HttpStatus.OK);
    }
}