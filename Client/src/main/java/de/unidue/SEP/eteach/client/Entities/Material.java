package de.unidue.SEP.eteach.client.Entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Material {

    private Integer id;
    private String materialString;
    private String bezeichnung;
    private String anmerkungen;
    private Veranstaltung veranstaltung;

    @JsonCreator
    public Material(@JsonProperty("id") Integer id,
                    @JsonProperty("materialString") String materialString,
                    @JsonProperty("bezeichnung") String bezeichnung,
                    @JsonProperty("anmerkungen") String anmerkungen,
                    @JsonProperty("veranstaltung") Veranstaltung veranstaltung
    ) {
        this.id=id;
        this.materialString=materialString;
        this.bezeichnung=bezeichnung;
        this.anmerkungen=anmerkungen;
        this.veranstaltung=veranstaltung;
    }

    public Material() {

    }

    @Override
    public String toString() {
        return this.bezeichnung + " (" + this.id+")";
    }

//    public Material material() {
//        Material material= new Material();
//        return material;
//    }
//
//    public Material(Veranstaltung veranstaltung){
//        this.veranstaltung=veranstaltung;
//    }
//
//    public Material(String bezeichnung) {
//        this.bezeichnung = bezeichnung;
//    }
//    public Material(String materialString, String bezeichnung, String anmerkungen, Veranstaltung veranstaltung) {
//        this.materialString = materialString;
//        this.bezeichnung = bezeichnung;
//        this.anmerkungen = anmerkungen;
//        this.veranstaltung = veranstaltung;
//    }


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
    public void setVeranstaltung(Veranstaltung veranstaltung) {
        this.veranstaltung = veranstaltung;
    }
    public Veranstaltung getLehrveranstaltung() {
        return veranstaltung;
    }
    public void setLehrveranstaltung(Veranstaltung veranstaltung) {
        this.veranstaltung = veranstaltung;
    }
}
