package org.loose.fis.sre.model;

import org.dizitart.no2.objects.Id;

public class Appointment {

    @Id
    private String username;
    private String LastName;
    private String FirstName;
    private String phone;
    private String date;
    private String time;
    private String valid;

    public Appointment(String username, String LastName, String FirstName, String phone, String date, String time, String valid) {
        this.username = username;
        this.LastName = LastName;
        this.FirstName = FirstName;
        this.phone = phone;
        this.date = date;
        this.time = time;
        this.valid = valid;
    }

    public Appointment() {
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String phone) {
        this.time = time;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }
}