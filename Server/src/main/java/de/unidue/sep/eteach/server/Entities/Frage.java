package de.unidue.sep.eteach.server.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement(name = "Fragenzeile")
public class Frage
{
    @Id
    @GeneratedValue
    public int id;
    @XmlElement(name ="Frage")
    public String fragestellung;
    @XmlElement(name ="AntwortA")
    public String antwortA;
    @XmlElement(name ="AntwortB")
    public String antwortB;
    @XmlElement(name ="AntwortC")
    public String antwortC;
    @XmlElement(name ="AntwortD")
    public String antwortD;

    // Exaxt wir in der Datei
    @XmlElement(name ="KorrekteAntwort")
    public String korrekteAntwort;
}
