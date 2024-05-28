package de.unidue.sep.eteach.server.Controller;

import de.unidue.sep.eteach.server.Entities.Material;
import de.unidue.sep.eteach.server.Entities.Veranstaltung;
import de.unidue.sep.eteach.server.Service.MaterialService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eteach/material")
public class MaterialController extends AppController {
    @Autowired
    MaterialService materialService;

    @GetMapping("/{id}")
    public ResponseEntity<Material> getById(@PathVariable Integer id) {
        return new ResponseEntity<>(materialService.getMaterialById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Material>> getAll() {
        return new ResponseEntity<>(materialService.getAll(), HttpStatus.OK);
    }

    @PostMapping(path = "/new", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Material> createMaterial(@RequestBody Material material) {
        return new ResponseEntity<Material>(materialService.createMaterial(material), HttpStatus.CREATED);
    }

    @PutMapping("/{materialId}/veranstaltung/{veranstaltungId}")
    public ResponseEntity<Material> veranstaltungsmaterialHinzufuegen(
            @PathVariable Integer materialId,
            @PathVariable Integer veranstaltungId
    ) {
        Material material = materialService.getMaterialById(materialId);
        Veranstaltung veranstaltung = veranstaltungService.getVeranstaltungById(veranstaltungId);
        material.veranstaltungsmaterialAnlegen(veranstaltung);
        return new ResponseEntity<Material>(materialService.createMaterial(material), HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Material> editMaterial(
            @RequestBody Material material,
            @PathVariable Integer id) {
        return new ResponseEntity<>(materialService.editMaterial(material), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Material> deleteMaterial(
            @PathVariable Integer id) {
        return new ResponseEntity<>(materialService.deleteMaterial(id), HttpStatus.OK);
    }

    @DeleteMapping("/{materialId}/veranstaltung/{veranstaltungId}")
    public ResponseEntity<Material> veranstaltungsmaterialEntfernen(
            @PathVariable Integer materialId,
            @PathVariable Integer veranstaltungId
    ) {
        Material material = materialService.getMaterialById(materialId);
        Veranstaltung veranstaltung = veranstaltungService.getVeranstaltungById(veranstaltungId);
        veranstaltung.veranstaltungsmaterialEntfernen(material);
        return new ResponseEntity<>(materialService.deleteMaterial(materialId), HttpStatus.OK);
    }
}
