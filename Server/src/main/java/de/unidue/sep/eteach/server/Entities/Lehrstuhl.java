package de.unidue.sep.eteach.server.Entities;

public enum Lehrstuhl {
    FINANZIERUNG("Finanzierung"),
    BETIREBSWIRTSCHAFTSLEHREII("Betriebswirtschaftslehre II"),
    DIGITALEINFRASTRUKTUR("Digitale Infrastruktur"),
    MENSCHMASCHINE("Mensch-Maschine Interaktionen"),
    CONTROLLING("Controlling"),
    STEUERN("Steuern");

    private String stringValue;

    Lehrstuhl(String stringValue) {
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
