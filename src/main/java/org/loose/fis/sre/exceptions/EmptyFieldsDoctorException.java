package org.loose.fis.sre.exceptions;

public class EmptyFieldsDoctorException extends Exception{
    public EmptyFieldsDoctorException(){ super(String.format("Complete all fields!")); }
}