package de.unidue.sep.eteach.server.Entities;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
@Component("Adresse")
@Entity
public class Adresse {
    @Id
    @GeneratedValue
    private int id;
    private String strasse;
    private int hausnummer;
    private int postleitzahl;
    private String ort;

    @OneToOne(mappedBy = "adresse")
    @JoinColumn(name = "nutzer_id", referencedColumnName = "id", nullable = false)
    private Nutzer nutzer;

    public Adresse() {
    }

    public int getId() {
        return id;
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

    public int getPostleitzahl() {
        return postleitzahl;
    }

    public void setPostleitzahl(int postleitzahl) {
        this.postleitzahl = postleitzahl;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }
}
