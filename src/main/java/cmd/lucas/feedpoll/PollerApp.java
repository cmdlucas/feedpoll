package cmd.lucas.feedpoll;

import cmd.lucas.feedpoll.domain.api.http.HttpRestRequestObject;
import cmd.lucas.feedpoll.domain.job.FeedPoller;
import cmd.lucas.feedpoll.domain.job.PollerAppSettings;
import cmd.lucas.feedpoll.domain.api.request.Query;
import cmd.lucas.feedpoll.domain.api.request.EverythingQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


@Component
public class PollerApp {
    private static final Logger log = LoggerFactory.getLogger(PollerApp.class);

    private final PollerAppSettings pollerAppSettings;
    private final ScheduledExecutorService executorService;
    private final HttpRestRequestObject httpRestRequestObject;


    @Autowired
    public PollerApp(PollerAppSettings pollerAppSettings, HttpRestRequestObject httpRestRequestObject) {
        this.pollerAppSettings = pollerAppSettings;
        this.httpRestRequestObject = httpRestRequestObject;
        this.executorService = pollerAppSettings.getExecutorService();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onStart(ApplicationReadyEvent event) {
        this.startPolling();
    }

    public void startPolling() {
        log.info("Poller Application started!");

        for(String keyword: pollerAppSettings.getKeywords()) {
            Query query = new EverythingQuery(
                    pollerAppSettings.getApiKey(), keyword, pollerAppSettings.getPageSize()
            );
            executorService.scheduleWithFixedDelay(
                    new FeedPoller(httpRestRequestObject.setQuery(query)),
                    pollerAppSettings.getInitialDelay(), pollerAppSettings.getDelay(), TimeUnit.MILLISECONDS
            );
        }

        log.info("All tasks scheduled. Polling started!");
    }

    @PreDestroy
    public void onStop() {
        executorService.shutdown();
    }
}
