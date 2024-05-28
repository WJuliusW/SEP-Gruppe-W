package de.unidue.sep.eteach.server.Entities;

public enum Veranstaltungstyp {
    VORLESUNG("Vorlesung"),
    SEMINAR("Seminar"),
    PROJEKTGRUPPE("Projektgruppe");

    private String stringValue;

    Veranstaltungstyp(String stringValue) {
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
