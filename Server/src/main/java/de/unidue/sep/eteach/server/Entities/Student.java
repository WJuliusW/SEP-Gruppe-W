package de.unidue.sep.eteach.server.Entities;

import lombok.RequiredArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Student")
public class Student extends Nutzer {
    private Studienfach studienfach;
    private Integer matrikelnummer = 1000000;
    private static int nextMatrikelnummer = 1000000;

    public Student() {
        this.matrikelnummer = this.nextMatrikelnummer;
        this.nextMatrikelnummer++;
    }

    public Studienfach getStudienfach() {
        return this.studienfach;
    }

    public void setStudienfach(Studienfach studienfach) {
        this.studienfach = studienfach;
    }

    public Integer getMatrikelnummer() {
        return this.matrikelnummer;
    }

    public static int getNextMatrikelnummer() {
        return nextMatrikelnummer;
    }

}
