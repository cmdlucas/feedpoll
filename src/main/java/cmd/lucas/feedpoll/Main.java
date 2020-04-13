package cmd.lucas.feedpoll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@SpringBootApplication
public class Main {

    private final PollerApp pollerApp;

    @Autowired
    Main(PollerApp pollerApp) {
        this.pollerApp = pollerApp;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void onStart(ContextRefreshedEvent event) {
        pollerApp.start();
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
