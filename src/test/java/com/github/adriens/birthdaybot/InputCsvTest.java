/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adriens.birthdaybot;

import java.io.File;
import java.sql.SQLException;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author salad74
 */
public class InputCsvTest {
    
    
    
    @Test
    public void isDefaultCsvFileWellFormatted(){
        boolean hasThrownException = false;
        try{
            BirthdaysProcessor processor = new BirthdaysProcessor();
            processor.prepareDatabase(SlackBirthdayGreeter.CSV_DEFAULT_FILENAME);
            
            hasThrownException=false;
        }
        catch(ClassNotFoundException | SQLException ex){
            hasThrownException = true;
        }
        Assert.assertFalse("We should not have thrown excepion", hasThrownException);
    }
    
    @Test
    public void doesDefaultCsvFileExists(){
        File csvFile = new File(SlackBirthdayGreeter.CSV_DEFAULT_FILENAME);
        Assert.assertTrue(SlackBirthdayGreeter.CSV_DEFAULT_FILENAME + " file is required", csvFile.exists());
    }
    
}
