package de.unidue.sep.eteach.server.Repositories;

import de.unidue.sep.eteach.server.Entities.Nutzer;
import de.unidue.sep.eteach.server.Entities.Veranstaltung;
import de.unidue.sep.eteach.server.Entities.Veranstaltungstyp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VeranstaltungRepo extends JpaRepository<Veranstaltung, Integer>  {
    List<Veranstaltung> findAllByVeranstaltungsteilnehmer(Nutzer nutzer);
    Veranstaltung findVeranstaltungById(Integer id);
    List<Veranstaltung> findAll();
    Veranstaltung getVeranstaltungByTitel(String title);

   //Meins
    List<Veranstaltung> getVeranstaltungsByTitelContainingIgnoreCaseAndVeranstaltungstypEquals(String titel, Veranstaltungstyp veranstaltungstyp);
    List<Veranstaltung> getVeranstaltungsByVeranstaltungstypEquals(Veranstaltungstyp veranstaltungstyp);
}

