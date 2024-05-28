package de.unidue.sep.eteach.server.Controller;

import de.unidue.sep.eteach.server.Entities.Lehrender;
import de.unidue.sep.eteach.server.Service.LehrenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/eteach/lehrender")
public class LehrenderController {

    @Autowired
    LehrenderService lehrenderService;

    @GetMapping("/{id}")
    public ResponseEntity<Lehrender> getById(@PathVariable Integer id) {
        return new ResponseEntity<>(lehrenderService.findLehrenderById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Lehrender>> getAll() {
        return new ResponseEntity<>(lehrenderService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<Lehrender> getByEmail(@PathVariable String email) {
        return new ResponseEntity<>(lehrenderService.getLehrenderByEmail(email), HttpStatus.OK);
    }


    @PostMapping(path = "/new", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Lehrender> createNewLehrender(@RequestBody Lehrender lehrender) {
        return new ResponseEntity<Lehrender>(lehrenderService.createLehrender(lehrender), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lehrender> editLehrender(
            @RequestBody Lehrender lehrender,
            @PathVariable String id) {
        return new ResponseEntity<>(lehrenderService.editLehrender(lehrender), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Lehrender> deleteLehrender(@PathVariable Integer id) {
        return new ResponseEntity<>(lehrenderService.deleteLehrender(id), HttpStatus.OK);
    }
}
