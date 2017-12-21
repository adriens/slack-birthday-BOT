/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adriens.birthdaybot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author salad74
 */
public class MavenExecRunner {
    public static void main(String[] args){
        Logger logger = LoggerFactory.getLogger(MavenExecRunner.class);
        logger.info("Getting inputs from command line...");
        
        
        /*
        String csv = "data/birthdays.csv";
        String giphyApiKey = "dc6zaTOxFJmzCZ";
        String slackWebhookUrl = "https://hooks.slack.com/services/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Z";
        */
        String csvFileName;
        String giphyApiKey;
        String slackWebHookUrl;
        
        // getting csv filename
        logger.info("Getting csvFilename from property <csvFileName> ...");
        csvFileName = System.getProperty("csvFileName", SlackBirthdayGreeter.CSV_DEFAULT_FILENAME);
        logger.info("Got <" + csvFileName + "> as input csvFileName");
        
        // getting giphyApiKey Key
        logger.info("Getting giphyApiKey from property <giphyApiKey> ....");
        giphyApiKey = System.getProperty("giphyApiKey");
        if(giphyApiKey == null || giphyApiKey.length() == 0){
            logger.error("<giphyApiKey> property is needed : please provide it : exiting with error status.");
            System.exit(1);
        }
        else {
            logger.info("Got giphyApiKey.");
        }
        
        // getting slackWebhookUrl
        logger.info("Getting slackWebhookUrl from propery <slackWebhookUrl> ...");
        slackWebHookUrl = System.getProperty("slackWebhookUrl");
        if(slackWebHookUrl == null || slackWebHookUrl.length() == 0){
            logger.error("<slackWebHookUrl> property is needed : please provide it : exiting with error status.");
            System.exit(1);
        }
        else {
            logger.info("Got slackWebHookUrl.");
        }
        
        logger.info("Got all parameters.");
        logger.info("About to send Birthday messages ...");
        
        BirthdaysProcessor processor = new BirthdaysProcessor(giphyApiKey, slackWebHookUrl);
        try{
            logger.info("Processing messages ...");
            processor.prepareDatabase(csvFileName);
            logger.info("Birthdays of the day loaded");
            logger.info("Sending messages ...");
            processor.sendMessages();
            logger.info("Messages successfully sent.");
            logger.info("Exit with success.\nBye.");
            System.exit(0);
        }
        catch(Exception ex){
            logger.error("Unable to process and send messages : " + ex.getMessage());
            logger.error("Exit with error code.");
            System.exit(1);
        }
        
    }
    
}
