/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.adriens.birthdaybot;

import at.mukprojects.giphy4j.Giphy;
import at.mukprojects.giphy4j.entity.search.SearchRandom;
import at.mukprojects.giphy4j.exception.GiphyException;
import java.util.ArrayList;
import java.util.Date;
import net.gpedro.integrations.slack.SlackApi;
import net.gpedro.integrations.slack.SlackAttachment;
import net.gpedro.integrations.slack.SlackMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author salad74
 */
public class SlackBirthdayGreeter {

    private String GiphyAPIKey;
    private String SlackWebhookUrl;

    public final static String BOT_GIPHY_KEYWORDS = "Happy birthday";
    public final static String BOT_SLACK_COLOR = "good";
    public final static String BOT_SLACK_AUTHOR_ICON = "https://png.icons8.com/ios/540/bot.png";
    public final static String BOT_SLACK_AUTHOR_LINK = "https://www.linkedin.com/in/adrien-sales-898b7270/";
    public final static String BOT_SLACK_AUTHOR_NAME = "birthday-BOT";
    public final static String BOT_SLACK_FOOTER = "Brought to you by birthday-BOT, visit https://github.com/adriens/slack-birthday-BOT for more.";
    public final static String BOT_SLACK_TITLE = "Time to celebrate a Happy Birthday !";
    public final static String BOT_SLACK_ICON = ":birthday:"; // Ref - http://www.emoji-cheat-sheet.com/
    public final static String BOT_SLACK_CHANNEL = "#birthdays";
    public final static String CSV_DEFAULT_FILENAME = "data/birthdays.csv";
    
    private final Logger logger = LoggerFactory.getLogger(SlackBirthdayGreeter.class);

    private Giphy giphy;
    private SlackApi slackApi;

    /**
     * @return the GiphyAPIKey
     */
    public String getGiphyAPIKey() {
        return GiphyAPIKey;
    }

    /**
     * @param GiphyAPIKey the GiphyAPIKey to set
     */
    public void setGiphyAPIKey(String GiphyAPIKey) {
        this.GiphyAPIKey = GiphyAPIKey;
    }

    /**
     * @return the SlackWebhookUrl
     */
    public String getSlackWebhookUrl() {
        return SlackWebhookUrl;
    }

    /**
     * @param SlackWebhookUrl the SlackWebhookUrl to set
     */
    public void setSlackWebhookUrl(String SlackWebhookUrl) {
        this.SlackWebhookUrl = SlackWebhookUrl;
    }

    public SlackBirthdayGreeter() {

    }

    public SlackBirthdayGreeter(String giphyApiKey, String slackWebhookUrl) {
        setGiphyAPIKey(giphyApiKey);
        setSlackWebhookUrl(slackWebhookUrl);
        this.giphy = new Giphy(giphyApiKey);
        this.slackApi = new SlackApi(slackWebhookUrl);
    }

    public void sayHappyBirthday(String targetUser, String attachmentTitle, String messageText) throws GiphyException {
        Giphy giphy = new Giphy(this.GiphyAPIKey);
        SearchRandom giphyData = giphy.searchRandom(this.BOT_GIPHY_KEYWORDS);
        String gifUrl = giphyData.getData().getImageOriginalUrl();
        SlackApi api = new SlackApi(getSlackWebhookUrl());
        String msg;
        if(targetUser == null || targetUser.length() == 0){
            msg = "Happy birthday @" + targetUser;
        }
        else{
            msg = messageText;
        }
        
        String attchTitle;
        if(attachmentTitle == null || attachmentTitle.length() == 0){
            attchTitle = "Time to celebrate a Happy Birthday !";
        }
        else{
            attchTitle = attachmentTitle;
        }
        
        SlackAttachment attchmnt = new SlackAttachment(msg + " " + gifUrl);
            attchmnt.setColor("good");
            attchmnt.setAuthorIcon(BOT_SLACK_AUTHOR_ICON);
            attchmnt.setAuthorLink(BOT_SLACK_AUTHOR_LINK);
            attchmnt.setAuthorName(BOT_SLACK_AUTHOR_NAME);
            attchmnt.setFooter(BOT_SLACK_FOOTER);
            //attchmnt.setFooterIcon("");
            attchmnt.setTitle(attachmentTitle);
            attchmnt.setText(msg);
            attchmnt.setImageUrl(gifUrl);
            attchmnt.setThumbUrl(gifUrl);
            attchmnt.setTimestamp(new Date());
            ArrayList<SlackAttachment> attchs = new ArrayList<>();
            attchs.add(attchmnt);
            
            api.call(new SlackMessage(BOT_SLACK_CHANNEL)
                    .setLinkNames(true)
                    .setIcon(BOT_SLACK_ICON)
                    .setUnfurlMedia(true)
                    .setAttachments(attchs));
    }
}
