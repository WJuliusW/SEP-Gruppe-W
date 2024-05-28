package de.unidue.SEP.eteach.client.Entities;

import java.io.Serializable;

public class Ereignis implements Serializable {
    private int id;
    private String titel;
    private String startDatum;
    private String endDatum;
    private String startZeit;
    private String endZeit;
    private Integer veranstaltung;
    private Integer nutzer;
    private String anmerkungen;
    public Integer erinnerungsIntervall;
    public String nameVorlesung;
    public Remindertyp remindertyp;


    public Ereignis() {
    }

    public Ereignis ereignis() {
        Ereignis ereignis = new Ereignis();
        return ereignis;
    }

    public Ereignis(Integer veranstaltung ) {

        this.veranstaltung = veranstaltung;
    }

    public Ereignis(String titel) {

    }

    public Ereignis(String titel, String startDatum, String endDatum, String startZeit, String endZeit) {
        this.titel=titel;
        this.startDatum = startDatum;
        this.endDatum = endDatum;
        this.startZeit = startZeit;
        this.endZeit = endZeit;

    }

    public Ereignis(String titel, String startDatum, String endDatum, String startZeit, String endZeit, String anmerkungen) {
        this.titel=titel;
        this.startDatum = startDatum;
        this.endDatum = endDatum;
        this.startZeit = startZeit;
        this.endZeit = endZeit;
        this.anmerkungen = anmerkungen;
    }

    public Integer getNutzer() {
        return nutzer;
    }

    public void setNutzer(Integer nutzer) {
        this.nutzer = nutzer;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getErinnerungsIntervall() {
        return erinnerungsIntervall;
    }

    public void setErinnerungsIntervall(Integer erinnerungsIntervall) {
        this.erinnerungsIntervall = erinnerungsIntervall;
    }

    public Remindertyp getRemindertyp() {
        return remindertyp;
    }

    public void setRemindertyp(Remindertyp remindertyp) {
        this.remindertyp = remindertyp;
    }

    public String getNameVorlesung() {
        return nameVorlesung;
    }

    public void setNameVorlesung(String nameVorlesung) {
        this.nameVorlesung = nameVorlesung;
    }

    public int getId() {
        return id;
    }

    public Integer getVeranstaltung() {
        return veranstaltung;
    }

    public void setVeranstaltung(Integer veranstaltung) {
        this.veranstaltung = veranstaltung;
    }

    public String getAnmerkungen() {
        return anmerkungen;
    }

    public void setAnmerkungen(String anmerkungen) {
        this.anmerkungen = anmerkungen;
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

    public void setStartZeit(String startZeit) {
        this.startZeit = startZeit;
    }

    public String getEndZeit() {
        return endZeit;
    }

    public void setEndZeit(String endZeit) {
        this.endZeit = endZeit;
    }
}
