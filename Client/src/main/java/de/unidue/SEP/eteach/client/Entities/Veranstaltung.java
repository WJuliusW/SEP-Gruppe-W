package de.unidue.SEP.eteach.client.Entities;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class Veranstaltung {
    private Integer id;
    private String titel;
    private Veranstaltungstyp veranstaltungstyp;
    private String beschreibung;
    private Lehrender organisator;
    private Semester semester;
    private List<Material> veranstaltungsmaterial;
    private List<Ereignis> veranstaltungstermine;
    private List<Nutzer> veranstaltungsteilnehmer;
    private List<ChatNachricht> chat;

    @JsonCreator
    public Veranstaltung(@JsonProperty("id") Integer id,
                         @JsonProperty("titel") String titel,
                         @JsonProperty("veranstaltungstyp") Veranstaltungstyp veranstaltungstyp,
                         @JsonProperty("beschreibung") String beschreibung,
                         @JsonProperty("organisator") Lehrender organisator,
                         @JsonProperty("semester") Semester semester,
                         @JsonProperty("veranstaltungsmaterial") List<Material> veranstaltungsmaterial,
                         @JsonProperty("veranstaltungstermine") List<Ereignis> veranstaltungstermine,
                         @JsonProperty("veranstaltungsteilnehmer") List<Student> veranstaltungsteilnehmer
    ) {
        this.id = id;
        this.titel = titel;
        this.veranstaltungstyp = veranstaltungstyp;
        this.organisator = organisator;
        this.semester = semester;
        this.beschreibung = beschreibung;
        this.veranstaltungsmaterial.addAll(veranstaltungsmaterial);
        this.veranstaltungstermine.addAll(veranstaltungstermine);
        this.veranstaltungsteilnehmer.addAll(veranstaltungsteilnehmer);
    }

    public Veranstaltung() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Material> getVeranstaltungsmaterial() {
        return veranstaltungsmaterial;
    }

    public void setVeranstaltungsmaterial(List<Material> veranstaltungsmaterial) {
        this.veranstaltungsmaterial = veranstaltungsmaterial;
    }

    public List<Ereignis> getVeranstaltungstermine() {
        return veranstaltungstermine;
    }

    public void setVeranstaltungstermine(List<Ereignis> veranstaltungstermine) {
        this.veranstaltungstermine = veranstaltungstermine;
    }

    public List<Nutzer> getVeranstaltungsteilnehmer() {
        return veranstaltungsteilnehmer;
    }

    public void setVeranstaltungsteilnehmer(List<Nutzer> veranstaltungsteilnehmer) {
        this.veranstaltungsteilnehmer = veranstaltungsteilnehmer;
    }

    public List<ChatNachricht> getChat() {
        return chat;
    }

    public void setChat(List<ChatNachricht> chat) {
        this.chat = chat;
    }

    public Veranstaltung(Nutzer currentNutzer) {
        this.organisator = organisator;
    }

    public Veranstaltung(Integer id){
        this.id = id;
    }

    public Veranstaltung(String titel, Lehrender lehrender, Veranstaltungstyp veranstaltungstyp, Semester semester) {
        this.titel = titel;
        this.organisator = lehrender;
        this.veranstaltungstyp = veranstaltungstyp;
        this.semester = semester;
    }

    public Veranstaltung(String titel, Lehrender lehrender, Veranstaltungstyp veranstaltungstyp, Semester semester, String beschreibung) {
        this.titel = titel;
        this.organisator = lehrender;
        this.veranstaltungstyp = veranstaltungstyp;
        this.semester = semester;
        this.beschreibung = beschreibung;
    }
    public String toString() {
        return "(" + this.veranstaltungstyp + ") " + this.titel + " - " + this.semester;
    }

    public int getId() {
        return id;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel=titel;
    }

    public Lehrender getOrganisator() {
        return organisator;
    }

    public void setOrganisator(Lehrender organisator) {
        this.organisator = organisator;
    }

    public Veranstaltungstyp getVeranstaltungstyp() {
        return veranstaltungstyp;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public void setVeranstaltungstyp(Veranstaltungstyp veranstaltungstyp) {
        this.veranstaltungstyp = veranstaltungstyp;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public List<Material> getMaterialList() {
        return veranstaltungsmaterial;
    }

    public void setMaterialList(List<Material> veranstaltungsmaterial) {
        this.veranstaltungsmaterial = veranstaltungsmaterial;
    }

    public List<Nutzer> getVeranstaltungsteilnehmerList() {
        return veranstaltungsteilnehmer;
    }

    public void setVeranstaltungsteilnehmerList(List<Nutzer> veranstaltungsteilnehmer) {
        this.veranstaltungsteilnehmer = veranstaltungsteilnehmer;
    }

    public List<Ereignis> getEreignisList() {
        return veranstaltungstermine;
    }

    public void setEreignisList(List<Ereignis> veranstaltungstermine) {
        this.veranstaltungstermine = veranstaltungstermine;
    }

    public Veranstaltungstyp getTyp()
    {
        return this.veranstaltungstyp;
    }

}
