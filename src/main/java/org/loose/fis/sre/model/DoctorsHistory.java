package org.loose.fis.sre.model;

public class DoctorsHistory {
    String LastName;
    String FirstName;
    String clinic;
    String specialty;

    public DoctorsHistory(String lastName, String firstName, String clinic, String specialty) {
        LastName = lastName;
        FirstName = firstName;
        this.clinic = clinic;
        this.specialty = specialty;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getClinic() {
        return clinic;
    }

    public void setClinic(String clinic) {
        this.clinic = clinic;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
}
