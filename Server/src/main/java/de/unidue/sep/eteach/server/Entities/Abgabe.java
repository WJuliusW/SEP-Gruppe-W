package de.unidue.sep.eteach.server.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Abgabe
{

    @Id
    @GeneratedValue
    public int id;
    public int frage;
    public boolean richtig;
    @OneToOne
    public Nutzer nutzer;
    @OneToOne
    public Quiz quiz;


    public Abgabe(){}

    public Abgabe(int a, boolean b)
    {
        frage = a;
        richtig = b;

    }

    public int getFrage() {
        return frage;
    }

    public void setFrage(int frage) {
        this.frage = frage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Nutzer getNutzer() {
        return nutzer;
    }

    public void setNutzer(Nutzer nutzer) {
        this.nutzer = nutzer;
    }

    public boolean isRichtig() {
        return richtig;
    }

    public void setRichtig(boolean richtig) {
        this.richtig = richtig;
    }
}
