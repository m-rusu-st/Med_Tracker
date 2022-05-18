package org.loose.fis.sre.exceptions;

public class AppointmentError extends Exception{
    public AppointmentError(){ super(String.format("Wrong time!")); }
}
