package de.unidue.sep.eteach.server.Entities;

import lombok.RequiredArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@DiscriminatorValue("Lehrender")
@RequiredArgsConstructor
public class Lehrender extends Nutzer {
    private Lehrstuhl lehrstuhl;
    private Studienfach forschungsgebiet;

    @OneToMany(mappedBy = "organisator",orphanRemoval = false)
    private Set<Veranstaltung> veranstaltungenOrganisation;


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
