package cmd.lucas.feedpoll.domain.job;

import cmd.lucas.feedpoll.domain.api.http.HttpRestRequestObject;
import cmd.lucas.feedpoll.util.opsresponse.GeneralResponse;
import cmd.lucas.feedpoll.util.opsresponse.ResponseType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class FeedPoller implements Runnable {
    private final HttpRestRequestObject httpRestRequestObject;

    public FeedPoller(HttpRestRequestObject httpRestRequestObject) {
        this.httpRestRequestObject = httpRestRequestObject;
    }

    @Override
    public void run() {
        try {
            // reset the control state on every run
            int tries = 0;
            boolean isFinished = false;

            // run this task and stop when, and only when, this task's action
            // is completed or has been attempted thrice

            while(!isFinished && tries++ < 3) {
                Optional<GeneralResponse> response = httpRestRequestObject.get();
                if(response.isPresent()) {
                    GeneralResponse generalResponse = response.get();
                    if(generalResponse.getType() == ResponseType.SUCCESS) {
                        isFinished = true;
                        log.info(generalResponse.getMessage());
                    } else {
                        log.error(generalResponse.getMessage());
                        // wait for 5 secs before trying again
                        Thread.sleep(5000);
                    }
                }
            }
        } catch (InterruptedException e) {
            log.error(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    private static final Logger log = LoggerFactory.getLogger(FeedPoller.class);
}
