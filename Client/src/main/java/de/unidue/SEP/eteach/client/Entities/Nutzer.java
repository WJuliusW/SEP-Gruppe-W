package de.unidue.SEP.eteach.client.Entities;

import java.util.List;

public  class Nutzer {

    private int id;
    public String vorname;
    public String nachname;
    public String email;
    public String passwort;
    public String  photoString;
    public Adresse adresse;
    private List<Veranstaltung> veranstaltungList;
    public String nutzer_typ;

    public Nutzer() {
    }

    public Nutzer(String vorname, String nachname, String email, String passwort, String photoString, Adresse adresse) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.email = email;
        this.passwort = passwort;
        this.photoString = photoString;
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return this.vorname + " " + this.nachname;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public String getNutzer_typ() {
        return nutzer_typ;
    }

    public int getId() {
        return id;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public String getPhotoString() {
        return photoString;
    }

    public void setPhotoString(String photoString) {
        this.photoString = photoString;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public List<Veranstaltung> getVeranstaltungList() {
        return veranstaltungList;
    }

    public void setVeranstaltungList(List<Veranstaltung> veranstaltungList) {
        this.veranstaltungList = veranstaltungList;
    }
}
