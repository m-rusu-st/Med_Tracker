package org.loose.fis.sre.exceptions;

public class EmptyUsernamePasswordFieldException extends Exception{

    public EmptyUsernamePasswordFieldException(){
        super(String.format("Please enter your data!"));
    }
}
