package de.unidue.sep.eteach.server.Service;

import de.unidue.sep.eteach.server.Controller.AppController;
import de.unidue.sep.eteach.server.Entities.Ereignis;
import de.unidue.sep.eteach.server.Entities.Nutzer;
import de.unidue.sep.eteach.server.Entities.Remindertyp;
import de.unidue.sep.eteach.server.Entities.Veranstaltung;
import de.unidue.sep.eteach.server.Repositories.EreignisRepo;
import de.unidue.sep.eteach.server.Repositories.NutzerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
public class EreignisService extends AppController {

     EreignisRepo ereignisRepo;
    NutzerRepo nutzerRepo;

    @Autowired
    public EreignisService(EreignisRepo ereignisRepo, NutzerRepo nutzerRepo) {

        this.ereignisRepo = ereignisRepo;
        this.nutzerRepo = nutzerRepo;
    }

    public Ereignis getEreignisById(Integer id) {
        return ereignisRepo.findEreignisById(id);
    }

    public Ereignis createEreignis(Ereignis ereignis) {
        return ereignisRepo.save(ereignis);
    }

    public List<Ereignis> getAll() {
        return ereignisRepo.findAll();
    }

    public Ereignis editEreignis(Ereignis ereignis) {

        return ereignisRepo.save(ereignis);

    }

    public Ereignis deleteEreignis(Integer id) {
        Ereignis ereignis = ereignisRepo.findEreignisById(id);
        ereignisRepo.delete(ereignis);
        return ereignis;
    }

    public List<Ereignis> getAllEreignisByNutzer(Integer nutzerId) {
        return ereignisRepo.getAllByNutzerEquals(nutzerId);
    }

    public List<Ereignis> getAllEreignisByVeranstaltung(Integer veranstaltungId) {
        return ereignisRepo.getAllByVeranstaltungEquals(veranstaltungId);
    }

    public List<Ereignis> getKalenderForNutzer(Integer nutzerId) {
        List<Ereignis> nutzerEreignisse = ereignisRepo.getAllByNutzerEquals(nutzerId);
        Nutzer nutzer = nutzerRepo.findNutzerById(nutzerId);
        Set<Veranstaltung> nutzerVeranstaltungen = nutzer.getVeranstaltungenEingeschrieben();

        nutzerVeranstaltungen.forEach(veranstaltung -> { // wie eine for each Schleife mit lambda funktion
            Set<Ereignis> veranstaltungsTermine = veranstaltung.getVeranstaltungstermine();
            nutzerEreignisse.addAll(veranstaltungsTermine);
        });
        nutzerEreignisse.sort(Ereignis.getComparator());

        return nutzerEreignisse; //Liste mit Ereignissen von Nutzer und allen Veranstaltungen in denen er eingeschrieben ist
    }


    public List<Ereignis> getKalenderForNutzerForDay(Integer nutzerId, String day) {
        List<Ereignis> tagesKalender = new ArrayList<>();
        List<Ereignis> nutzerEreignisse = ereignisRepo.getAllByNutzerEquals(nutzerId);
        nutzerEreignisse.forEach(ereignis -> {
            if (ereignis.getStartDatum().equals(day)) {
                tagesKalender.add(ereignis);
            }
        });

        Nutzer nutzer = nutzerRepo.findNutzerById(nutzerId);
        Set<Veranstaltung> nutzerVeranstaltungen = nutzer.getVeranstaltungenEingeschrieben();

        nutzerVeranstaltungen.forEach(veranstaltung -> { // wie eine for each Schleife mit lambda funktion
            List<Ereignis> veranstaltungsTagesKalender = new ArrayList<>();
            Set<Ereignis> veranstaltungsTermine = veranstaltung.getVeranstaltungstermine();
            veranstaltungsTermine.forEach(ereignis -> {
                if (ereignis.getStartDatum().equals(day)) {
                    veranstaltungsTagesKalender.add(ereignis);
                }
            });
            tagesKalender.addAll(veranstaltungsTagesKalender);
        });
        tagesKalender.sort(Ereignis.getComparator());

        return tagesKalender;
    }

    public List<Ereignis> getReminderEreignisseByNutzer(Integer nutzerId) throws ParseException {
        return getReminderEreignisseByNutzer(nutzerId, new Date());
    }

    public List<Ereignis> getReminderEreignisseByNutzer(Integer nutzerId, Date date) throws ParseException {
        List<Ereignis> nutzerKalender = getKalenderForNutzer(nutzerId);
        List<Ereignis> reminderEreignisse = new ArrayList<>();

        for (Ereignis ereignis : nutzerKalender) {
            if (ereignis.getRemindertyp() == null ||  ereignis.getStartZeit() == null ||  ereignis.getStartDatum() == null || ereignis.getErinnerungsIntervall() == null ) {continue;} // Wenn Datum oder Zeit oder Remidner nicht gesetzt -> überspringen
            DateFormat dateFormatToday = new SimpleDateFormat("dd.MM.yyyy, HH:mm:ss", Locale.ENGLISH); //Datums Formatierer für Heutiges Datum
            DateFormat dateFormatEreignis = new SimpleDateFormat("yyyy-MM-dd, HH:mm:ss", Locale.ENGLISH); //Datums Formatierer für Ereignis Datum
            Date todayDate = dateFormatToday.parse(date.toLocaleString()); //Zeichenkette in Objekt formatiert (hier Datum)
            String startZeit = ereignis.getStartZeit();
            if (startZeit.length() < 6) { // Startzeit Sekunden hinzufügen wenn nötig
                startZeit += ":00";
            }
            Date ereignisDate = dateFormatEreignis.parse(ereignis.getStartDatum() + ", " + startZeit); // Startdatum und Zeit zusammen zu Date Objekt konvertieren

            ereignisDate.setHours(ereignisDate.getHours() - ereignis.getErinnerungsIntervall()); // ErinnerungsINtervall Stundne hinzufügen
            if (ereignisDate.getDay() == todayDate.getDay() && ereignisDate.before(todayDate)) { // Wenn ereignisDate am selben tag und vor aktueller Zeit
                reminderEreignisse.add(ereignis); // Dann zu reminder liste hinzufügen
            }
        }

        return reminderEreignisse;
    }


}








