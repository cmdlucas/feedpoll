package cmd.lucas.feedpoll.config;

import cmd.lucas.feedpoll.domain.service.NewsApi;
import cmd.lucas.feedpoll.qualifier.NewsApiConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;

@Configuration
@ComponentScan(basePackages = "cmd.lucas.feedpoll")
public class OutgoingRequestApiConfig {

    @Bean
    @Autowired
    NewsApi newsApi(@NewsApiConnection Retrofit retrofit) {
        return retrofit.create(NewsApi.class);
    }
}
