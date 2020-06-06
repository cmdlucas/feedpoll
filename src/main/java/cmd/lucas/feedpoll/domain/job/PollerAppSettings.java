package cmd.lucas.feedpoll.domain.job;

import cmd.lucas.feedpoll.config.qualifier.*;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ScheduledExecutorService;

@Component
@Getter
public class PollerAppSettings {
    private final int delay;
    private final int initialDelay;
    private final String apiKey;
    private final String[] keywords;
    private final int pageSize;
    private final ScheduledExecutorService executorService;

    @Autowired
    public PollerAppSettings(ScheduledExecutorService executorService,
                             @Delay int delay, @InitialDelay int initialDelay,
                             @NewsApiPageSize int pageSize,
                             @NewsApiKey String apiKey, @NewsApiKeywords String[] keywords) {
        this.delay = delay;
        this.initialDelay = initialDelay;
        this.pageSize = pageSize;
        this.apiKey = apiKey;
        this.keywords = keywords;
        this.executorService = executorService;
    }
}
