package de.unidue.sep.eteach.server.Entities;


import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Set;
@Data
@Component("Veranstaltung")
@Entity
public class Veranstaltung {
    @Id
    @GeneratedValue
    private Integer id;
    private String titel;
    private Veranstaltungstyp veranstaltungstyp;
    @Column(columnDefinition = "TEXT")
    private String beschreibung;
    private Semester semester;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lehrender_id", referencedColumnName = "id")
    private Lehrender organisator;

    @OneToMany(mappedBy = "veranstaltung")
    private Set<Material> veranstaltungsmaterial;

    @OneToMany(mappedBy = "veranstaltung")
    private Set<Ereignis> veranstaltungstermine;

    @ManyToMany(mappedBy = "veranstaltungenEingeschrieben")
    private Set<Nutzer> veranstaltungsteilnehmer;

    @OneToMany(mappedBy = "veranstaltung")
    private Set<ChatNachricht> chat;

    public Veranstaltung() {
    }

    public void veranstaltungsorganisatorFestlegen(Lehrender lehrender) {
        this.organisator = lehrender;
    }

    public void veranstaltungsmaterialEntfernen(Material material) {
        this.veranstaltungsmaterial.remove(material);
    }

//    public Lehrender getOrganisator() {
//        return organisator;
//    }
//
//    public Semester getSemester() {
//        return semester;
//    }

//    public void veranstaltungssemesterFestlegen(Semester semester) {
//        this.semester=semester;
//    }
//
//    public Set<Material> getVeranstaltungsmaterial() {
//        return veranstaltungsmaterial;
//    }
//
//    public Set<Ereignis> getVeranstaltungstermine() {
//        return veranstaltungstermine;
//    }
//
//    public Set<Student> getVeranstaltungsteilnehmer() {
//        return veranstaltungsteilnehmer;
//    }
//
//
//    public Integer getId() {
//        return id;
//    }
//
//    public String getTitel() {
//        return titel;
//    }
//
//    public void setTitel(String titel) {
//        this.titel = titel;
//    }
//
//    public String getVeranstaltungstyp() {
//        return veranstaltungstyp;
//    }
//
//    public void setVeranstaltungstyp(String veranstaltungstyp) {
//        this.veranstaltungstyp = veranstaltungstyp;
//    }
//
//    public String getBeschreibung() {
//        return beschreibung;
//    }
//
//    public void setBeschreibung(String beschreibung) {
//        this.beschreibung = beschreibung;
//    }


}