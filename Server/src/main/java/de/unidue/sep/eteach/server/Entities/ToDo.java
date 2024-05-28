package de.unidue.sep.eteach.server.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class ToDo
{
    @Id
    @GeneratedValue
    public int id;
    public String aufgabe;

    @OneToOne
    public Nutzer aufgabensteller;
   

    @OneToOne
    public Veranstaltung veranstaltung;

    public ToDo() {

    }

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
