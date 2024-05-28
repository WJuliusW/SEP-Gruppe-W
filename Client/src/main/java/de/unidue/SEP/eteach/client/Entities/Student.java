package de.unidue.SEP.eteach.client.Entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Student extends Nutzer {

    private Integer matrikelnummer;
    private Studienfach studienfach;

    @JsonCreator
    public Student(@JsonProperty("vorname") String vorname,
                   @JsonProperty("nachname") String nachname,
                   @JsonProperty("email") String email,
                   @JsonProperty("password") String passwort,
                   @JsonProperty("photoString") String photoString,
                   @JsonProperty("matrikelnummer") Integer matrikelnummer,
                   @JsonProperty("studienfach") Studienfach studienfach,
                   @JsonProperty("adresse") Adresse adresse
//                   @JsonProperty("nutzer_typ") String nutzer_typ
    ) {
        super(vorname, nachname, email, passwort, photoString, adresse);
        this.studienfach = studienfach;
        this.matrikelnummer = matrikelnummer;
    }

    public Student() {
    }

    public Student(String vorname, String nachname, String email, String passwort, String photoString, Studienfach studienfach, Adresse adresse) {
        super(vorname, nachname, email, passwort, photoString, adresse);
        this.studienfach = studienfach;
      //  this.matrikelnummer = matrikelnummer;
    }

    public int getMatrikelnummer() {
        return matrikelnummer;
    }

    public Studienfach getStudienfach() {
        return studienfach;
    }

    public void setStudienfach(Studienfach studienfach) {
        this.studienfach = studienfach;
    }


}
