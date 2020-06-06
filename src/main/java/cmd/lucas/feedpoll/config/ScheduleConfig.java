package cmd.lucas.feedpoll.config;

import cmd.lucas.feedpoll.config.qualifier.NewsApiKeywords;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Configuration
@ComponentScan(basePackages = "cmd.lucas.feedpoll")
public class ScheduleConfig {
    @Bean
    @NewsApiKeywords
    String[] newsApiKeywords() {
        return new String[]{
                "bitcoin", "cdc", "trump", "startup", "corona", "covid-19", "covid", "ethereum", "nigeria", "europe",
                "merkel", "buhari", "dangote", "entertainment", "football", "tennis", "politics", "wealth", "economy"
        };
    }

    @Bean
    ScheduledExecutorService scheduledExecutorService() {
        return Executors.newScheduledThreadPool(4);
    }

}
