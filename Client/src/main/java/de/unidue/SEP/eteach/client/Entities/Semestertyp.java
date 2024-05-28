package de.unidue.SEP.eteach.client.Entities;

public enum Semestertyp {
    SOMMERSEMESTER("Sommersemester"),
    WINTERSEMESTER("Wintersemester");

    private String stringValue;

    Semestertyp(String stringValue) {
        this.stringValue = stringValue;
    }

    public String getStringValue() {
        return stringValue;
    }
}
