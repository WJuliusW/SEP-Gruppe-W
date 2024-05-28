package de.unidue.SEP.eteach.client.Entities;

public enum Studienfach {
    ANGLISTIK("Anglistik"),
    GERMANISTIK("Germanistik"),
    SPORTWISSENSCHAFTEN("Sportwissenschaften"),
    INFORMATIK("Informatik"),
    BETRIEBSWIRTSCHAFTSLEHRE("Betriebswirtschaftslehre");

    private String stringValue;

    Studienfach(String stringValue) {
        this.stringValue = stringValue;
    }

    @Override
    public String toString() {
        return stringValue;
    }

    public String getStringValue() {
        return stringValue;
    }

}
