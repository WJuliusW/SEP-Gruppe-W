package de.unidue.SEP.eteach.client.Entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "Quizfragen")
public class Quiz
{
    @XmlTransient
    public int id;
    @XmlTransient
    public String name;
    @XmlTransient
    public Veranstaltung passendeVeranstaltung;
    @XmlElement(name = "Fragenzeile")
    public List<Frage> fragenList;
    //Statistiken
    @XmlTransient
    public int versuchsAnzahl,erfolgreicheVersuche;
    // public int anzahlVersucheProStudent;//LENGTH = veranstaltung.getTeilnehmer.length
    //public int anzahlVersucheProFrage; //LENGTH = fragenList.length

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Veranstaltung getPassendeVeranstaltung() {
        return passendeVeranstaltung;
    }

    public void setPassendeVeranstaltung(Veranstaltung passendeVeranstaltung) {
        this.passendeVeranstaltung = passendeVeranstaltung;
    }

    @XmlTransient
    public List<Frage> getFragenList() {
        return fragenList;
    }

    public void setFragenList(List<Frage> fragenList) {
        this.fragenList = fragenList;
    }

    public int getVersuchsAnzahl() {
        return versuchsAnzahl;
    }

    public void setVersuchsAnzahl(int versuchsAnzahl) {
        this.versuchsAnzahl = versuchsAnzahl;
    }

    public int getErfolgreicheVersuche() {
        return erfolgreicheVersuche;
    }

    public void setErfolgreicheVersuche(int erfolgreicheVersuche) {
        this.erfolgreicheVersuche = erfolgreicheVersuche;
    }
}