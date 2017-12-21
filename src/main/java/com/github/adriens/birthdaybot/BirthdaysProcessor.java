/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adriens.birthdaybot;

import at.mukprojects.giphy4j.exception.GiphyException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author salad74
 */
public class BirthdaysProcessor {

    private final Logger logger = LoggerFactory.getLogger(BirthdaysProcessor.class);
    private Connection conn;

    private String giphyApiKey;
    private String slackWebhookUrl;
            
    public BirthdaysProcessor() {

    }
    
    public BirthdaysProcessor(String giphyApiKey, String slackWebhookUrl){
        this.giphyApiKey = giphyApiKey;
        this.slackWebhookUrl = slackWebhookUrl;
    }

    public void prepareDatabase(String csvFilename) throws ClassNotFoundException, SQLException {
        // create in memory database and keep connection
        Class.forName("org.h2.Driver");

        this.conn = DriverManager.getConnection("jdbc:h2:mem:birthday-bot", "sa", "");
        Statement stmt = conn.createStatement();

        // create table as load csv in h2 table
        stmt.execute("CREATE TABLE _birthdays (username varchar primary key, dob DATE not null, msg varchar) AS SELECT * from CSVREAD('" + csvFilename + "',NULL,'charset=UTF-8 fieldSeparator=,');");
        logger.info("Staging birthdays table successfully loaded.");
    }

    public ArrayList<Birthday> getBirthdaysOfTheDay() throws SQLException {
        logger.info("Retrieving birtdays of today ...");
        ArrayList<Birthday> out = new ArrayList<Birthday>();
        Statement stmt = this.conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from _birthdays where  extract (month from dob) = extract(month from now()) and extract (DD from dob) = extract(DD from now())");
        String lUser;
        Date lDate;
        String lMsg;
        Birthday lBirth;
        int counter = 0;
        while (rs.next()){
            lUser = rs.getString("username");
            lDate = rs.getDate("dob");
            logger.info(rs.getString("dob"));
            logger.info(rs.getString("username"));
            logger.info(rs.getString("msg"));
            lMsg = rs.getString("msg");
            lBirth = new Birthday(lUser, lDate, lMsg);
            out.add(lBirth);
            counter++;
            logger.info("Found new birtday to add : " + lBirth);
        }
        logger.info("Found <" + counter + "> birthday(s) to celebrate today.");
        return out;
    }
    public ArrayList<Birthday> getAllBirthdays() throws SQLException {
        logger.info("Retrieving birtdays of today ...");
        ArrayList<Birthday> out = new ArrayList<Birthday>();
        Statement stmt = this.conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from _birthdays");
        String lUser;
        Date lDate;
        String lMsg;
        Birthday lBirth;
        int counter = 0;
        while (rs.next()){
            lUser = rs.getString("username");
            lDate = rs.getDate("dob");
            logger.info(rs.getString("dob"));
            logger.info(rs.getString("username"));
            logger.info(rs.getString("msg"));
            lMsg = rs.getString("msg");
            lBirth = new Birthday(lUser, lDate, lMsg);
            out.add(lBirth);
            counter++;
            logger.info("Found new birtday to add : " + lBirth);
        }
        logger.info("Found <" + counter + "> birthday(s)");
        return out;
    }
    
    public void sendMessages() throws SQLException, GiphyException {
        ArrayList<Birthday> bdates = getBirthdaysOfTheDay();
        SlackBirthdayGreeter greeter = new SlackBirthdayGreeter(this.giphyApiKey, this.slackWebhookUrl);
        // create the giphy object
        Iterator<Birthday> iter = getBirthdaysOfTheDay().iterator();
        Birthday lBirth;
        while(iter.hasNext()){
            lBirth = iter.next();
            greeter.sayHappyBirthday(lBirth.getUserName(), null, lBirth.getMessage());
        }
        // fetch people who's birthday is today
        //Statement stmt = conn.createStatement();
        //stmt.executeQuery("select * from birthdays where dob = now()");
    }
    
    public static void main(String[] args){
        // parameters
        String csv = "data/birthdays.csv";
        // fake accounts
        String giphyApiKey = "dc6zaTOxFJmzCZ";
        String slackWebhookUrl = "https://hooks.slack.com/services/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Z";
        // do the job
        BirthdaysProcessor processor = new BirthdaysProcessor(giphyApiKey, slackWebhookUrl);
        try{
            processor.prepareDatabase(csv);
            processor.sendMessages();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        System.exit(0);
    }
}
