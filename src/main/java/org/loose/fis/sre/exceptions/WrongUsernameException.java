package org.loose.fis.sre.exceptions;

public class WrongUsernameException extends Exception{
    private String username;

    public WrongUsernameException(String username){
        super(String.format("An account with the username %s does not exists!" + "\n" + "Try again!", username));
        this.username = username;
    }

    public String getUsername(){
        return username;
    }
}