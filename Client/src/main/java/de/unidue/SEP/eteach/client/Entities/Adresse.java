package de.unidue.SEP.eteach.client.Entities;

public class Adresse {

    public String strasse;
    public int hausnummer;
    public String ort;
    public int postleitzahl;
    public Adresse() {
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public int getHausnummer() {
        return hausnummer;
    }

    public void setHausnummer(int hausnummer) {
        this.hausnummer = hausnummer;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public int getPostleitzahl() {
        return postleitzahl;
    }

    public void setPostleitzahl(int postleitzahl) {
        this.postleitzahl = postleitzahl;
    }

    public Adresse(String strasse, int hausnummer, String ort, int postleitzahl) {
        this.strasse = strasse;
        this.hausnummer = hausnummer;
        this.ort = ort;
        this.postleitzahl = postleitzahl;
    }

    public String getOrt() {
        return ort;
    }
}
