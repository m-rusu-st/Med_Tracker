package org.loose.fis.sre.model;

import org.dizitart.no2.objects.Id;

public class Appointment {

    @Id
    private String LastName;
    private String FirstName;
    private String phone;
    private String date;
    private String time;
    private String valid;

    public Appointment(String LastName, String FirstName, String phone, String date, String time, String valid) {
        this.LastName = LastName;
        this.FirstName = FirstName;
        this.phone = phone;
        this.date = date;
        this.time = time;
        this.valid = valid;
    }

    public Appointment() {
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }
}