package de.unidue.sep.eteach.server.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

@Component("Ereignis")
@Entity
public class Ereignis {
    @Id
    @GeneratedValue
    private int id;
    private String titel;
    private String startDatum;
    private String endDatum;
    private String startZeit;
    private String endZeit;
    private Integer veranstaltung;
    private Integer nutzer;
    private String anmerkungen;
    private Integer erinnerungsIntervall;
    public String nameVorlesung;
    public Remindertyp remindertyp;

    public Ereignis() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStartZeit(String startZeit) {
        this.startZeit = startZeit;
    }

    public void setEndZeit(String endZeit) {
        this.endZeit = endZeit;
    }

    public String getAnmerkungen() {
        return anmerkungen;
    }

    public void setAnmerkungen(String anmerkungen) {
        this.anmerkungen = anmerkungen;
    }

    public String getNameVorlesung() {
        return nameVorlesung;
    }

    public void setNameVorlesung(String nameVorlesung) {
        this.nameVorlesung = nameVorlesung;
    }

    public Remindertyp getRemindertyp() {
        return remindertyp;
    }

    public void setRemindertyp(Remindertyp remindertyp) {
        this.remindertyp = remindertyp;
    }

    public int getId() {
        return id;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getStartDatum() {
        return startDatum;
    }

    public void setStartDatum(String startDatum) {
        this.startDatum = startDatum;
    }

    public String getEndDatum() {
        return endDatum;
    }

    public void setEndDatum(String endDatum) {
        this.endDatum = endDatum;
    }

    public String getStartZeit() {
        return startZeit;
    }

    public String getEndZeit() {
        return endZeit;
    }

    public Integer getErinnerungsIntervall() {
        return erinnerungsIntervall;
    }

    public void setErinnerungsIntervall(Integer erinnerungsIntervall) {
        this.erinnerungsIntervall = erinnerungsIntervall;
    }

    public Integer getVeranstaltung() {
        return veranstaltung;
    }

    public void setVeranstaltung(Integer veranstaltung) {
        this.veranstaltung = veranstaltung;
    }

    public Integer getNutzer() {
        return nutzer;
    }

    public void setNutzer(Integer nutzer) {
        this.nutzer = nutzer;
    }
    //static damit wir sie ohne Ereignis abrufen können
    public static EreignisComparator getComparator(){
        return new EreignisComparator();
    }
    //vergleicht Ereignisse um zu sortieren
    static class EreignisComparator implements Comparator<Ereignis>{

        @SneakyThrows
        @Override
        public int compare(Ereignis o1, Ereignis o2) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH); //geben das Format
            Date ereignis1Date = dateFormat.parse(o1.startDatum); //Zeichenkette in Objekt formatiert (hier Datum)
            Date ereignis2Date = dateFormat.parse(o2.startDatum);

            return ereignis1Date.compareTo(ereignis2Date);
        }
    }
}
