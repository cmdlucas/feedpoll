package cmd.lucas.feedpoll.config;

import cmd.lucas.feedpoll.config.qualifier.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@ComponentScan(basePackages = "cmd.lucas.feedpoll")
@PropertySource("classpath:config/app.properties")
public class AppConfig {

    @Autowired
    Environment environment;

    @Bean
    @HelloMessage
    public String getHelloMessage() {
        return environment.getProperty("app.hello.message");
    }

    @Bean
    @NewsApiKey
    public String getApiKey() {
        return environment.getProperty("newsapi.apikey");
    }

    @Bean
    @NewsApiBaseUrl
    public String getBaseUrl() {
        return environment.getProperty("newsapi.baseurl");
    }

    @Bean
    @NewsApiEverythingEndpoint
    public String getEverythingEndpoint() {
        return environment.getProperty("newsapi.endpoint.everything");
    }

    @Bean
    @NewsApiTopHeadlinesEndpoint
    public String getTopHeadlinesEndpoint() {
        return environment.getProperty("newsapi.endpoint.topheadlines");
    }

    @Bean
    @NewsApiPageSize
    public int getPageSize() {
        String pageSize = environment.getProperty("newsapi.request.pagesize", "30");
        return Integer.parseInt(pageSize);
    }

    @Bean
    @InitialDelay
    public int getInitialDelay() {
        String initialDelay = environment.getProperty("pollerapp.scheduler.initialdelay.ms", "500");
        return Integer.parseInt(initialDelay);
    }

    @Bean
    @Delay
    public int getDelay() {
        String delay = environment.getProperty("pollerapp.scheduler.delay.ms", "90000");
        return Integer.parseInt(delay);
    }
}
