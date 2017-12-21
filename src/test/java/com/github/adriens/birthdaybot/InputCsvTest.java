/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adriens.birthdaybot;

import java.io.File;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
        boolean badDateFormating = false;
        try{
            BirthdaysProcessor processor = new BirthdaysProcessor();
            processor.prepareDatabase(SlackBirthdayGreeter.CSV_DEFAULT_FILENAME);
            
            hasThrownException=false;
            
            ArrayList<Birthday> list = processor.getAllBirthdays();
            System.out.println("Found " + list.size());
            Assert.assertTrue(list.size() > 0);
            // hte list is not null now check date are well formatted YYYY-MM-DD
            Iterator<Birthday> birthIter = list.iterator();
            Birthday lBirth;
            String birthDate;
             SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            while (birthIter.hasNext()){
                lBirth = birthIter.next();
                Date convertedCurrentDate = sdf.parse(lBirth.getBirthDate().toString());
                System.out.println("birthDate : <" + lBirth.getBirthDate() + ">");
            }
            
            
        }
        catch(ClassNotFoundException | SQLException ex){
            hasThrownException = true;
        }
        catch(ParseException ex){
            badDateFormating = true;
            System.err.println(ex.getMessage());
        }
        Assert.assertFalse("We should not have thrown excepion", hasThrownException);
        Assert.assertFalse("date format must be YYYY-MM-DD", badDateFormating);
    }
    
    @Test
    public void doesDefaultCsvFileExists(){
        File csvFile = new File(SlackBirthdayGreeter.CSV_DEFAULT_FILENAME);
        Assert.assertTrue(SlackBirthdayGreeter.CSV_DEFAULT_FILENAME + " file is required", csvFile.exists());
    }
    
}
