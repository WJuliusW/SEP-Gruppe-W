package de.unidue.sep.eteach.server.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ChatNachricht {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    /*
    JsonIgnore verhindert eine endlosschleife, da eine veranstaltung mehrere Chatnachrichten hat und jede chatnachricht hat eine Veranstaltung
     */

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "veranstaltung_id", referencedColumnName = "id")
    private Veranstaltung veranstaltung;


    @OneToOne
    @JoinColumn
    private Nutzer absender;

    private String inhalt;

    private Date zeitstempel;


    public ChatNachricht() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Veranstaltung getVeranstaltung() {
        return veranstaltung;
    }

    public void setVeranstaltung(Veranstaltung veranstaltung) {
        this.veranstaltung = veranstaltung;
    }

    public Nutzer getAbsender() {
        return absender;
    }

    public void setAbsender(Nutzer absender) {
        this.absender = absender;
    }

    public String getInhalt() {
        return inhalt;
    }

    public void setInhalt(String inhalt) {
        this.inhalt = inhalt;
    }

    public Date getZeitstempel() {
        return zeitstempel;
    }

    public void setZeitstempel(Date zeitstempel) {
        this.zeitstempel = zeitstempel;
    }
}