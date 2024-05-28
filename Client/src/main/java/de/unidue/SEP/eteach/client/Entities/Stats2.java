package de.unidue.SEP.eteach.client.Entities;

public class Stats2
{
    public int frage;
    public int anzahl;

    public Stats2(int frage)
    {
        this.frage = frage;
    }

    public int getFrage() {
        return frage;
    }

    public void setFrage(int frage) {
        this.frage = frage;
    }

    public int getAnzahl() {
        return anzahl;
    }

    public void setAnzahl(int anzahl) {
        this.anzahl = anzahl;
    }
}
