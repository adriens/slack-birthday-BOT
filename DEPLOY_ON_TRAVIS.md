# Deploy birthday-BOT on Travis CI

The aim of this deploy scenario is to totally rely on free cloud services that are,
in our case :

- Github : where the job's code ***and datas*** will rely
- Travis : that will trigger the run on a daily basis
- Slack : where the messages are sent

# Fork project

Fork the project on your account.

Replace fill the ```.travis.yml``` with the following content :

```
language: java
jdk:
- oraclejdk8
install: mvn install
script:
- export TZ=YourTimeZone
- mvn exec:java -DgiphyApiKey=$GIPHY_API_KEY -DslackWebhookUrl=$SLACK_WEBHOOK_URL
```
And replace YourTimeZone by the good timezone : https://en.wikipedia.org/wiki/List_of_tz_database_time_zones

# Encrypt keys

Encrypt your GIPHY and SlackWebhook url keys with your own values
thanks to [The Travis Client)[https://github.com/travis-ci/travis.rb].

Run the following commands on the project directory

```
travis encrypt GIPHY_API_KEY="YOUR_GIPHY_API_KEY_HERE" --add
travis encrypt SLACK_WEBHOOK_URL="YOUR_SLACK_WEBHOOK_URL"  --add
```

After that, the ```.travis.yml``` should have been updated with a section
that should look like the one below :

```
env:
  global:
  - secure: SUJ98MY9qqWiSmmq5cAKoOz3L4Xee5+Lo8mDiw5ZpMGDgE7s2zLYg2BK5a0xrdhQBwdB2WCtwrQyLTOSGvngb6FUizmdOUnpTkvsUPD2f65MBsuq9zPXha/VdmCCPCv45dVWkumBN0l064XWQqjR3QamwKLTgeRvCQumiXAX3+CJtP0wgMpzabpYTRQOz73MATijAmR5Vzl1v0ZTlPN27HS5sKnhMZdNEJFzGvsI+V1c8PyGKfR2Qxmrd1a+tAPPeoxjtjzcMnutYBrJYXJZSZKEAsnhTAvyFjmzoaQAR9gn6RDfz8U8O2io4rJm0tKljbTdoj+QVP8W9WMKan1Abw4RH07szpXbYY65htp1tKBmGjLh4sxXcmXv3FImxrGl+N3V7ROT98lh4zuGANddbUyz30SzhJHFWBl8SWudEuMoMl4s0DYga72QZRgTqtHsaAqxrzo80aq8nV+R0AG2Aa/W+dA54oIK1sVymacfLsY2htN9XwCxMKtGpdl3XCzVLlQoDqd5LOep/pygnMHO6IdKjOez/aNpXs0vZU8i6BuJxGg3FWk3J/7AQG8v6v1iEqii/10L2omdTb5pLL63DaK77ajExckhg+u8tv/Co9rDFtG2rAQiSrTnhF+hzkDp18HqlCnvC2bZ1s5bRTEmI=
  - secure: lPjCJnJHe6JcrMLSdigjTVO4MggKKp36PeYufu03tIRnDE7if0jX44hAe4ReibbSw48zkMY/MzLe7uzy5t7J0ehUaWA0GYtAkJlLRAoz7V6OUnFov7CRqvB0V90yzKkrTUcZk2gjBdBG5KmgU/MsAP9tZMJLCQ4zwpBvECmUJPDSe7+q6QMLg8d8SeOsPY7R/3L8MbDWrIvj88nGG0yCUK1D9EjoWULtJqwgzpBbLuTZMlV4zTQXZoqeot/cUbC/y4HHl15a/VGFuDadD3u7Riv/pBjAFaglhgXTSvpwgX1H6AAH9ISq1i34UWnt4K3PJD6vTNCIZFtxad6Gsk5NpM3FhNcSSaiLbnoTPcJVpXinf9E4Rk72RmrTg7M17E7prYvoylE9fM7nyvE0YFpbyt85K4lDlwtDWfo5OThHF5O1icXQ7QzTKR2/of5SQ3/IK90rLFMFobzK3IEGNROmdbgvSLhsHPU2xB5D1x+UEAFYDxTncEU56Sl8LY8wSm/MPFNC2Vd/TRHGobccqf7l24d3P/vfdK/g4dqttSeZ+7yVB6XaNMzA1HMObJxBXnf34EXfSd8+uVzT3EiC4cqJXOdTa1/Qu7wlXWn02S+wSwXIU3fhLloAAD+NCl671L9Wxhrba9/Qd/S0ZBiv2wI=
```

At this stage, you're almost done.

# Birthdays database

Update the default database by adding your own birthdays in ```data/birthdays.csv````...add
a test line to the current day targetting yourself so you'll test notifications.

# Setup your Travis cron job

...and you're done.

# Git tips

To better manage birthdays greetings, you can create a dedicated branch and
cron job only on that branch ;-p ...hence you are ready to perform PR on the project.
