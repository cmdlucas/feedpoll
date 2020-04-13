package cmd.lucas.feedpoll;

import cmd.lucas.feedpoll.domain.http.contract.HttpRestRequestObject;
import cmd.lucas.feedpoll.domain.job.FeedPoller;
import cmd.lucas.feedpoll.util.ApiKeys;
import cmd.lucas.feedpoll.util.apirequest.contract.Query;
import cmd.lucas.feedpoll.util.apirequest.newsapi.EverythingQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class PollerApp {
    public static final Logger log = LoggerFactory.getLogger(Main.class);

    private final HttpRestRequestObject httpRestRequestObject;

    private final String[] keywords = new String[]{
            "bitcoin", "cdc", "trump", "startup", "corona", "covid-19", "covid", "ethereum", "nigeria", "europe",
            "merkel", "buhari", "dangote", "entertainment", "football", "tennis", "politics", "wealth", "economy"
    };

    public PollerApp(HttpRestRequestObject httpRestRequestObject) {
        this.httpRestRequestObject = httpRestRequestObject;
    }

    public void start() {
        // we want to be able to get new articles for as many keywords
        // as possible, and within a short period of time. But we have to be careful
        // about the number of threads we spin off. Otherwise, we might lose the gains
        // to context switching cost we set this number to 4 with an assumption that
        // most computers would have at least 4 cores

        log.info("Poller Application started!");

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(4);

        for(String keyword: keywords) {
            Query query = new EverythingQuery(
                    ApiKeys.NEWS_API_DOT_ORG, keyword, 25, 1
            );
            executorService.scheduleWithFixedDelay(
                    new FeedPoller(query, httpRestRequestObject),
                    0, 90000, TimeUnit.MILLISECONDS
            );
        }

        log.info("All tasks scheduled. Polling started!");
    }
}
