package de.unidue.SEP.eteach.client.Entities;

public enum Semester {

    SOSE21("Sommersemester 2021"),
    WISE2122("Wintersemester 2021/2022"),
    SOSE22("Sommersemester 2022"),
    WISE2223("Wintersemester 2022/2023"),
    SOSE23("Sommersemester 2023"),
    WISE2324("Wintersemester 2023/2024"),
    SOSE24("Sommersemester 2024"),
    WISE2425("Wintersemester 2024/2025");


    private String stringValue;

    Semester(String stringValue) {
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
