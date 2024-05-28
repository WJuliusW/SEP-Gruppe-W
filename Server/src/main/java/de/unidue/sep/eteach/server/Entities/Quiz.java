package de.unidue.sep.eteach.server.Entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

@Entity
@XmlRootElement(name = "Quizfragen")
public class Quiz
{

    @Id
    @GeneratedValue
    @XmlTransient
    public int id;
    @XmlTransient
    public String name;

    @OneToOne
    @XmlTransient
    public Veranstaltung passendeVeranstaltung;
    @OneToMany(cascade = CascadeType.ALL)
    @XmlElement(name = "Fragenzeile")
    public List<Frage> fragenList;


    //Statistiken
    @XmlTransient
    public int versuchsAnzahl,erfolgreicheVersuche;

    public Quiz() {
    }

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
