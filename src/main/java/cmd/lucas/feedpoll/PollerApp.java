package cmd.lucas.feedpoll;

import cmd.lucas.feedpoll.domain.http.contract.HttpRestRequestObject;
import cmd.lucas.feedpoll.domain.job.FeedPoller;
import cmd.lucas.feedpoll.util.ApiKeys;
import cmd.lucas.feedpoll.util.Timer;
import cmd.lucas.feedpoll.util.apirequest.contract.Query;
import cmd.lucas.feedpoll.util.apirequest.newsapi.EverythingQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


@Component
public class PollerApp {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    private final HttpRestRequestObject httpRestRequestObject;

    private String[] keywords = new String[]{
            "bitcoin", "cdc", "trump", "startup", "corona", "covid-19", "covid", "ethereum", "nigeria", "europe",
            "merkel", "buhari", "dangote", "entertainment", "football", "tennis", "politics", "wealth", "economy"
    };

    private int corePoolSize = 4;
    private ScheduledExecutorService executorService;

    @Autowired
    public PollerApp(HttpRestRequestObject httpRestRequestObject) {
        this.httpRestRequestObject = httpRestRequestObject;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onStart(ApplicationReadyEvent event) {
        this.start();
    }

    public void start() {
        log.info("Poller Application started!");

        setExecutorService();

        for(String keyword: keywords) {
            Query query = new EverythingQuery(
                    ApiKeys.NEWS_API_DOT_ORG, keyword, 30, 1
            );
            getExecutorService().scheduleWithFixedDelay(
                    new FeedPoller(httpRestRequestObject.setQuery(query)),
                    Timer.INITIAL_DELAY_MS, Timer.REPEAT_SCHEDULE_MS, TimeUnit.MILLISECONDS
            );
        }

        log.info("All tasks scheduled. Polling started!");
    }

    private void setExecutorService() {
        this.executorService = Executors.newScheduledThreadPool(corePoolSize);
    }

    public ScheduledExecutorService getExecutorService() {
        return executorService;
    }

    public String[] getKeywords() {
        return keywords;
    }

    public PollerApp setKeywords(String[] keywords) {
        this.keywords = keywords;
        return this;
    }

    public int getCorePoolSize() {
        return corePoolSize;
    }

    public PollerApp setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
        return this;
    }
}
