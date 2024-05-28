package de.unidue.SEP.eteach.client.Entities;

import de.unidue.SEP.eteach.client.Entities.Lehrstuhl;

public class Lehrender extends Nutzer {

    private Lehrstuhl lehrstuhl;
    private Studienfach forschungsgebiet;

    public Lehrender() {

    }

    public Lehrender(String vorname, String nachname, String password, String email, String photoString, Adresse adresse, Lehrstuhl lehrstuhl, Studienfach forschungsgebiet) {
        super(vorname, nachname, password, email, photoString, adresse);
        this.lehrstuhl = lehrstuhl;
        this.forschungsgebiet = forschungsgebiet;
    }

    public Lehrstuhl getLehrstuhl() {
        return lehrstuhl;
    }

    public void setLehrstuhl(Lehrstuhl lehrstuhl) {
        this.lehrstuhl = lehrstuhl;
    }

    public Studienfach getForschungsgebiet() {
        return forschungsgebiet;
    }

    public void setForschungsgebiet(Studienfach forschungsgebiet) {
        this.forschungsgebiet = forschungsgebiet;
    }
}
