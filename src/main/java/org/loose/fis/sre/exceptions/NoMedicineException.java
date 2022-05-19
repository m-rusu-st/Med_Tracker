package org.loose.fis.sre.exceptions;

public class NoMedicineException extends Exception{
    public NoMedicineException(){ super(String.format("Wrong medicine! Try agin!")); }
}
