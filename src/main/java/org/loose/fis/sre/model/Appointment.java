package org.loose.fis.sre.model;

import org.dizitart.no2.objects.Id;
import org.loose.fis.sre.services.UserService;

public class Appointment {

    @Id
    private String codUnic;

    private String username;
    private String LastName;
    private String FirstName;
    private String phone;
    private String date;
    private String time;
    private String doctor;
    private String valid;

    public static int contor;
    public Appointment(String username, String LastName, String FirstName, String phone, String date, String time, String doctor, String valid) {
        this.username = username;
        this.LastName = LastName;
        this.FirstName = FirstName;
        this.phone = phone;
        this.date = date;
        this.time = time;
        this.doctor = doctor;
        this.valid = valid;

        contor = UserService.nrElementsApp() + 1;
        codUnic = String.valueOf(contor);
    }

    public Appointment() {
    }

    public String getCodUnic() {
        return codUnic;
    }

    public void setCodUnic(String codUnic) {
        this.codUnic = codUnic;
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

    public void setTime(String time) {
        this.time = time;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }
}