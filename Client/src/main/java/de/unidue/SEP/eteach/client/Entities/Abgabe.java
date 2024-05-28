package de.unidue.SEP.eteach.client.Entities;

import de.unidue.SEP.eteach.client.Context;

import static de.unidue.SEP.eteach.client.Context.currentQuiz;

public class Abgabe
{

    public int id;
    public int frage;
    public boolean richtig;
    public Nutzer nutzer;
    public Quiz quiz;
//INteger wie viele das bearbeitet haben und INTeger

    public Abgabe(int a,boolean b)
    {
        frage = a;
        richtig = b;
        nutzer = Context.currentNutzer;
        quiz = currentQuiz;
    }

    public int getFrage() {
        return frage;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
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
