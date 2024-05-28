package de.unidue.sep.eteach.server.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component("Lehrmaterial")
@Entity
public class Material {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(columnDefinition = "TEXT")
    private String materialString;
    private String bezeichnung;
    @Column(columnDefinition = "TEXT")
    private String anmerkungen;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "veranstaltung_Id", referencedColumnName = "id")
    private Veranstaltung veranstaltung;

    public Material() {
    }

    public void veranstaltungsmaterialAnlegen(Veranstaltung veranstaltung) {
        this.veranstaltung=veranstaltung;
    }

    public Integer getId() {
        return id;
    }

    public String getMaterialString() {
        return materialString;
    }

    public void setMaterialString(String materialString) {
        this.materialString = materialString;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public String getAnmerkungen() {
        return anmerkungen;
    }

    public void setAnmerkungen(String anmerkungen) {
        this.anmerkungen = anmerkungen;
    }

    public Veranstaltung getVeranstaltung() {
        return veranstaltung;
    }

}
