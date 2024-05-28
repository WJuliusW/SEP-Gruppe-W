package de.unidue.sep.eteach.server.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Freundschaft
{
    @Id
    @GeneratedValue
    public int id;

    @OneToOne
    public Nutzer nutzer1, nutzer2;


    public Boolean freundschaftAngenommen;

    public int anfrageID;

    public Freundschaft(Nutzer nutzer1, Nutzer nutzer2, Boolean freundschaftAngenommen) {
        this.nutzer1 = nutzer1;
        this.nutzer2 = nutzer2;
        this.freundschaftAngenommen = freundschaftAngenommen;
    }

    public Freundschaft() {

    }


    public int getAnfrageID() {
        return anfrageID;
    }

    public void setAnfrageID(int anfrageID) {
        this.anfrageID = anfrageID;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Nutzer getNutzer1() {
        return nutzer1;
    }

    public void setNutzer1(Nutzer nutzer1) {
        this.nutzer1 = nutzer1;
    }

    public Nutzer getNutzer2() {
        return nutzer2;
    }

    public void setNutzer2(Nutzer nutzer2) {
        this.nutzer2 = nutzer2;
    }

    public Boolean getFreundschaftAngenommen() {
        return freundschaftAngenommen;
    }

    public void setFreundschaftAngenommen(Boolean freundschaftAngenommen) {
        this.freundschaftAngenommen = freundschaftAngenommen;
    }
}
