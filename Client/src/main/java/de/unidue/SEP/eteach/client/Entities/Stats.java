package de.unidue.SEP.eteach.client.Entities;

public class Stats
{
    public Nutzer nutzer;
    public int anzahl;

    public Stats(Nutzer nutzer)
    {
        this.nutzer = nutzer;
        anzahl = 0;
    }

    public Nutzer getNutzer() {
        return nutzer;
    }

    public void setNutzer(Nutzer nutzer) {
        this.nutzer = nutzer;
    }

    public int getAnzahl() {
        return anzahl;
    }

    public void setAnzahl(int anzahl) {
        this.anzahl = anzahl;
    }
}
