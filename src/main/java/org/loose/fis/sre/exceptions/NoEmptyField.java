package org.loose.fis.sre.exceptions;

public class NoEmptyField extends Exception{
    public NoEmptyField(){ super(String.format("Complete all fields!")); }
}
