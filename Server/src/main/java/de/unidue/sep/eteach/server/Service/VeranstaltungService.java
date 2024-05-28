package de.unidue.sep.eteach.server.Service;

import de.unidue.sep.eteach.server.Entities.Nutzer;
import de.unidue.sep.eteach.server.Entities.Veranstaltung;
import de.unidue.sep.eteach.server.Entities.Veranstaltungstyp;
import de.unidue.sep.eteach.server.Repositories.VeranstaltungRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VeranstaltungService {

    VeranstaltungRepo veranstaltungRepo;

    @Autowired
    public VeranstaltungService(VeranstaltungRepo veranstaltungRepo) {
        this.veranstaltungRepo = veranstaltungRepo;
    }

    public Veranstaltung getVeranstaltungById(Integer id) {
        return veranstaltungRepo.findVeranstaltungById(id);
    }

    public Veranstaltung getVeranstaltungByTitle(String title) {
        return veranstaltungRepo.getVeranstaltungByTitel(title);
    }

    public List<Veranstaltung> getVeranstaltungenByTeilnehmer(Nutzer nutzer) {
        return veranstaltungRepo.findAllByVeranstaltungsteilnehmer(nutzer);
    }

    public Veranstaltung createVeranstaltung(Veranstaltung veranstaltung) {
        return veranstaltungRepo.save(veranstaltung);
    }

    public List<Veranstaltung> getAll() {
        return veranstaltungRepo.findAll();
    }

    public Veranstaltung editVeranstaltung(Veranstaltung veranstaltungEditiert) {
        return veranstaltungRepo.save(veranstaltungEditiert);
    }

    public Veranstaltung deleteVeranstaltung(Integer id) {
        Veranstaltung veranstaltung = veranstaltungRepo.findVeranstaltungById(id);
        veranstaltungRepo.delete(veranstaltung);
        return veranstaltung;
    }
   //Meins
    public List<Veranstaltung> searchProjektgruppeByTitel(String titel){

        return veranstaltungRepo.getVeranstaltungsByTitelContainingIgnoreCaseAndVeranstaltungstypEquals(titel, Veranstaltungstyp.PROJEKTGRUPPE);
    }

    public List<Veranstaltung> getVeranstaltungByVeranstaltungstyp(Veranstaltungstyp veranstaltungstyp){

        return veranstaltungRepo.getVeranstaltungsByVeranstaltungstypEquals(veranstaltungstyp);
    }
}
