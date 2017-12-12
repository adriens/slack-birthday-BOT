# Slack birthday-BOT

A slack BOT that simply reminds Birthdays the funny way with animated random [Giphy](https://giphy.com/) GIFs... for free !

# Goal

The goal of this project is to make a  "BOT" that wishes happy birthday, on a dedicated channel.

***NB : As integration is made thanks to a Webhook, you don't have to add an app to your Slack Team... good for people who are
using free plans on Slack.***

# Requirements

To make this integration operational, all you need is :

- A slack Webhook url dedicated to the  ```birthdays``` channel (private or public)
- A Giphy API token
- A place to run your daily Job ([Travis cron jobs](https://docs.travis-ci.com/user/cron-jobs/) is a good place to do that so all your stuff is in the cloud)...but you can run it wherever you like
- A csv file that contains birthdays data
- A ```maven``` runtime (to make things easier to run ...)

All of these items are available for free.

# Command line

If your are using default csv file (```data/birthdays.csv```), just run :

```mvn exec:java -DgiphyApiKey=YOUR_GIPHY_API_KEY -DslackWebhookUrl=YOUR_SLACK_WEBHOOK_URL```

If you want to use a custom csv file use the fully qualified command line :

```mvn exec:java -DcsvFileName=CSV_FILENAME_PATH -DgiphyApiKey=YOUR_GIPHY_API_KEY -DslackWebhookUrl=YOUR_SLACK_WEBHOOK_URL```

# How to get slack incoming webhook url



# Deployment instructions

# Acknowledgements

- Thanks to [Gabriel Pedro](https://github.com/gpedro) for his very efficient [slack-webhook](https://github.com/gpedro/slack-webhook) library, and very kind support on my features requests
- Thanks to [Mathias Markl](https://github.com/keshrath) for his [Giphy4J](https://github.com/keshrath/Giphy4J) Giphy lib


