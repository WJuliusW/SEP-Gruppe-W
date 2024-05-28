package de.unidue.sep.eteach.server.Entities;

import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.util.Date;

@Entity
public class PrivatNachricht {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int id;

    @OneToOne
    @JoinColumn
    private Nutzer absender;

    @OneToOne
    @JoinColumn
    private Nutzer empfaenger;

    private String inhalt;

    private Date zeitstempel;

    private  PrivatNachrichtTyp typ;

    public PrivatNachricht() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Nutzer getEmpfaenger() {
        return empfaenger;
    }
    public void setEmpfaenger(Nutzer empfaenger) {
        this.empfaenger = empfaenger;
    }

    public PrivatNachrichtTyp getTyp() {
        return typ;
    }

    public void setTyp(PrivatNachrichtTyp typ) {
        this.typ = typ;
    }
}
