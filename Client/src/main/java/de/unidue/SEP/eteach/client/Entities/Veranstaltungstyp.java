package de.unidue.SEP.eteach.client.Entities;

import javafx.collections.ObservableList;

public enum Veranstaltungstyp  {
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
