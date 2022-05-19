package org.loose.fis.sre.model;

import org.loose.fis.sre.services.UserService;

public class AppointmentDetails {
    String name;
    String hour;
    String date;
    String doctorAndLocation;
    String valid;

    public AppointmentDetails(String name, String hour, String date, String doctorAndLocation, String valid) {
        this.name = name;
        this.hour = hour;
        this.date = date;
        this.doctorAndLocation = doctorAndLocation;
        this.valid = valid;
    }

    public String getName() {
        return name;
    }

    public String getHour() {
        return hour;
    }

    public String getDate() {
        return date;
    }

    public String getDoctorAndLocation() {
        return doctorAndLocation;
    }

    public String getValid() {
        return valid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDoctorAndLocation(String doctorAndLocation) {
        this.doctorAndLocation = doctorAndLocation;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }
}
