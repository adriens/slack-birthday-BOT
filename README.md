[![Build Status](https://travis-ci.org/adriens/slack-birthday-BOT.svg?branch=master)](https://travis-ci.org/adriens/slack-birthday-BOT) [![Dependency Status](https://beta.gemnasium.com/badges/github.com/adriens/slack-birthday-BOT.svg)](https://beta.gemnasium.com/projects/github.com/adriens/slack-birthday-BOT) [![Gitter](https://badges.gitter.im/adriens/slack-birthday-BOT.svg)](https://gitter.im/adriens/slack-birthday-BOT?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge)

# Slack birthday-BOT

A slack BOT that simply reminds Birthdays the funny way with animated random [Giphy](https://giphy.com/) GIFs... for free.

![Demo](https://media.giphy.com/media/l3mZcB5FOVEWDGNsk/giphy.gif)


# Goal

The goal of this project is to make a  "BOT" that wishes happy birthday, on a dedicated channel.

***NB : As integration is made thanks to a Webhook, you don't have to add an app to your Slack Team... good for people who are
using free plans on Slack.***

# Requirements

To make this integration operational, all you need is :

- A slack Webhook url dedicated to the  ```birthdays``` channel (private or public)
- A Giphy API token
- A place to run your daily Job ([Travis cron jobs](https://docs.travis-ci.com/user/cron-jobs/) is a good place to do that so all your stuff is in the cloud)...but you can run it wherever you like
- A csv file that contains birthdays data (please take a look at ```data/birthdays.csv``` to get a running example)
- A ```maven``` runtime (to make things easier to run ...)

All of these items are available for free.

# Setup birthdays csv (flat) database

It's all in ```data/birthdays.csv``` : 

- ```username``` and ```dob``` are mandatory,
- ```username``` must be unique in the csv (the slack username, without the ```@```)
- ```dob``` is in format ```YYYY-MM-DD```

# Command line

If your are using default csv file (```data/birthdays.csv```), just run :

```mvn exec:java -DgiphyApiKey=YOUR_GIPHY_API_KEY -DslackWebhookUrl=YOUR_SLACK_WEBHOOK_URL```

If you want to use a custom csv file use the fully qualified command line :

```mvn exec:java -DcsvFileName=CSV_FILENAME_PATH -DgiphyApiKey=YOUR_GIPHY_API_KEY -DslackWebhookUrl=YOUR_SLACK_WEBHOOK_URL```

# How to get slack incoming webhook url

- First, install the ```Incoming WebHooks``` app : https://slack.com/apps/A0F7XDUAZ-incoming-webhooks
- Configure it to target the ```birthdays``` channel
- Keep the url and keep it secret

# Deployment instructions

You can set in up localy on your workstation, on a server but...the best way is to deploy it on the cloud...
and Travis CI can help, see dedicated section for that.

# Tips

To better manage birthdays greetings, you can create a dedicated branch and
cron job only on that branch ;-p ...hence you are ready to perform PR on the project.

Take care to schedule cron in the middle of the morning to get the best effect :
it's much more fun to get a live slack than to discover on when you
arrive at the office : nothing beats the Slack "tac tac" with a punching GIF !

# LinkedIn article

https://www.linkedin.com/pulse/birthdays-bots-slack-travis-ci-cron-jobs-all-free-fun-adrien-sales/


# Acknowledgements

- Thanks to [Gabriel Pedro](https://github.com/gpedro) for his very efficient [slack-webhook](https://github.com/gpedro/slack-webhook) library, and very kind support on my features requests
- Thanks to [Mathias Markl](https://github.com/keshrath) for his [Giphy4J](https://github.com/keshrath/Giphy4J) Giphy lib
