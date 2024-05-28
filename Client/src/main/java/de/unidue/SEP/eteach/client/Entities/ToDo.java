package de.unidue.SEP.eteach.client.Entities;

public class ToDo
{

    public int id;
    public String aufgabe;
    public Nutzer aufgabensteller;
    public Veranstaltung veranstaltung;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAufgabe() {
        return aufgabe;
    }

    public void setAufgabe(String aufgabe) {
        this.aufgabe = aufgabe;
    }

    public Nutzer getAufgabensteller() {
        return aufgabensteller;
    }

    public void setAufgabensteller(Nutzer aufgabensteller) {
        this.aufgabensteller = aufgabensteller;
    }

    public Veranstaltung getVeranstaltung() {
        return veranstaltung;
    }

    public void setVeranstaltung(Veranstaltung veranstaltung) {
        this.veranstaltung = veranstaltung;
    }
}
