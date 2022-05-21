package org.loose.fis.sre.model;

public class DoctorsHistory {
    String username;
    String LastName;
    String FirstName;
    String clinic;
    String specialty;

    public DoctorsHistory(String username, String lastName, String firstName, String clinic, String specialty) {
        this.username = username;
        LastName = lastName;
        FirstName = firstName;
        this.clinic = clinic;
        this.specialty = specialty;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
