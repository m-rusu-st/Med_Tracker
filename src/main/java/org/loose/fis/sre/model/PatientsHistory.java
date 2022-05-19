package org.loose.fis.sre.model;

public class PatientsHistory {
    String LastName;
    String FirstName;
    String phone;
    String medicamentation;

    public PatientsHistory(String lastName, String firstName, String phone, String medicamentation) {
        LastName = lastName;
        FirstName = firstName;
        this.phone = phone;
        this.medicamentation = medicamentation;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMedicamentation() {
        return medicamentation;
    }

    public void setMedicamentation(String medicamentation) {
        this.medicamentation = medicamentation;
    }
}
