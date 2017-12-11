/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adriens.birthdaybot;

import java.util.Date;

/**
 *
 * @author salad74
 */
public class Birthday {

    private String userName;
    private Date birthDate;
    private String message;
    
    public Birthday(){
        
    }
    public Birthday(String userName, Date birthDate){
        this.userName = userName;
        this.birthDate = birthDate;
    }
    
    public Birthday(String userName, Date birthDate, String message){
        this.userName = userName;
        this.birthDate = birthDate;
        this.message = message;
    }
    
    public String toString(){
        String out = "<" + userName + "> born the <" + birthDate + "> msg : <" + message + ">";
        return out;
    }
    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the birthDate
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * @param birthDate the birthDate to set
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
    
}
