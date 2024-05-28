package de.unidue.sep.eteach.server.Entities;


public enum Semestertyp {
    SOMMERSEMESTER("Sommersemester"),
    WINTERSEMESTER("Wintersemester");

    private String stringValue;

    Semestertyp(String stringValue) {
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
