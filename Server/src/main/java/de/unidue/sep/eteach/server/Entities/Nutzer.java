package de.unidue.sep.eteach.server.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "Nutzer")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "nutzer_typ", discriminatorType = DiscriminatorType.STRING)
public class Nutzer {
    @Id
    @GeneratedValue
    private int id;
    private String vorname;
    private String nachname;
    private String email;
    private String passwort;
    @Column(columnDefinition = "TEXT")
    private String photoString;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "adresse_id", referencedColumnName = "id")
    private Adresse adresse;
    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "eingeschriebenVeranstaltungen",
            joinColumns = @JoinColumn(name = "nutzer_id"),
            inverseJoinColumns = @JoinColumn(name = "veranstaltung_id"))
    private Set<Veranstaltung> veranstaltungenEingeschrieben;



    @Column(name="nutzer_typ", insertable = false, updatable = false)
    protected String nutzer_typ;

    @OneToMany(mappedBy = "nutzer")
    private Set<Ereignis> kalender;

    /*
    @JsonManagedReference
    @ManyToMany
    private Set<Nutzer> freunde;
    */

    public Nutzer() {
    }

    public Nutzer(String vorname, String nachname, String email, String passwort, String photoString) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.email = email;
        this.passwort = passwort;
        this.photoString = photoString;
    }

    public Nutzer(String vorname, String nachname, String email, String passwort, String photoString, Adresse adresse) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.email = email;
        this.passwort = passwort;
        this.photoString = photoString;
        this.adresse = adresse;
    }

    public void nutzerInVeranstaltungEinschreiben(Veranstaltung veranstaltung) {
        veranstaltungenEingeschrieben.add(veranstaltung);
    }

    public void nutzerAusVeranstaltungEntfernen(Veranstaltung veranstaltung) {
        veranstaltungenEingeschrieben.remove(veranstaltung);
    }

    public Set<Veranstaltung> getVeranstaltungenEingeschrieben() {
        return veranstaltungenEingeschrieben;
    }

    public void nutzeradresseAnlegen(Adresse adresse) {
        this.adresse = adresse;
    }

    public int getId() {
        return id;
    }

    public String getNutzer_typ() {
        return nutzer_typ;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public String getPhotoString() {
        return photoString;
    }

    public void setPhotoString(String photoString) {
        this.photoString = photoString;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public void setId(int id) {
        this.id = id;
    }
/*
    public Set<Nutzer> getFreunde() { return freunde; }

    public void setFreunde(Set<Nutzer> freunde) { this.freunde = freunde; }

 */
}

