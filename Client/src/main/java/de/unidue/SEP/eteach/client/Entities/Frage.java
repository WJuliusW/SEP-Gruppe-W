package de.unidue.SEP.eteach.client.Entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Fragenzeile")
public class Frage
{
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
    @XmlElement(name ="KorrekteAntwort")
    public String korrekteAntwort;

}