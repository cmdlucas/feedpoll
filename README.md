This project polls for tweets from Twitter and stores valuable information

###Install 

#####Git

First clone to your machine

`git clone https://github.com/cmdlucas/feedpoll.git`

Then in your IDE (e.g. IntelliJ), under Maven > Plugins > spring-boot, double-click on

`spring-boot:run`

Periodic polling would start automatically. You will now be able to view the last stored news in your browser on [https://localhost:8080/news](https://localhost:8080/news) 

#####Docker

To pull and run this project as a Docker container, you can run this command from your console:

`docker run cmdlucas/feedpoll`

###Testing

In your IDE (e.g. IntelliJ), under Maven > Lifecycle, double-click `test`



