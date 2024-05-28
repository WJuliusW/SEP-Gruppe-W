package de.unidue.SEP.eteach.client.Entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

@Getter
@Setter
public class ChatNachricht {

    private int id;

    private Veranstaltung veranstaltung;

    public Nutzer absender;

    private String inhalt;

    private Date zeitstempel;

    //Generiet aus dem jason eine Chatnachricht
    @JsonCreator
    public ChatNachricht(
            @JsonProperty("id") Integer id,
            @JsonProperty ("inhalt") String inhalt,
            @JsonProperty("zeitstempel") String zeitstempel
    ) throws ParseException {

        this.id = id;
        this.inhalt = inhalt;
        this.zeitstempel = DateFormat.getDateInstance().parse(zeitstempel);
    }

    public ChatNachricht() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Veranstaltung getVeranstaltung() {
        return veranstaltung;
    }

    public void setVeranstaltung(Veranstaltung veranstaltung) {
        this.veranstaltung = veranstaltung;
    }

    public Nutzer getAbsender() {
        return absender;
    }

    public void setAbsender(Nutzer absender) {
        this.absender = absender;
    }

    public String getInhalt() {
        return inhalt;
    }

    public void setInhalt(String inhalt) {
        this.inhalt = inhalt;
    }

    public Date getZeitstempel() {
        return zeitstempel;
    }

    public void setZeitstempel(Date zeitstempel) {
        this.zeitstempel = zeitstempel;
    }
}