
package de.unidue.SEP.eteach.client.Entities;

import java.util.Date;

public class PrivatNachricht {

    private int id;
    private Nutzer absender;
    private Nutzer empfaenger;
    private String inhalt;
    private Date zeitstempel;
    private PrivatNachrichtenTyp typ;

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

    public Nutzer getEmpfaenger() {
        return empfaenger;
    }

    public void setEmpfaenger(Nutzer empfaenger) {
        this.empfaenger = empfaenger;
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

    public PrivatNachrichtenTyp getTyp() {
        return typ;
    }

    public void setTyp(PrivatNachrichtenTyp typ) {
        this.typ = typ;
    }
}