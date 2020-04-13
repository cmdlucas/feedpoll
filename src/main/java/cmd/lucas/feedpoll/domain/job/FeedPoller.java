package cmd.lucas.feedpoll.domain.job;

import cmd.lucas.feedpoll.domain.http.contract.HttpRestRequestObject;
import cmd.lucas.feedpoll.util.apirequest.contract.Query;
import cmd.lucas.feedpoll.util.opsresponse.GeneralResponse;
import cmd.lucas.feedpoll.util.opsresponse.ResponseType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class FeedPoller extends Thread {
    private final Query query;
    private final HttpRestRequestObject httpRestRequestObject;

    private boolean isFinished = false;

    public FeedPoller(Query query, HttpRestRequestObject httpRestRequestObject) {
        super();
        this.query = query;
        this.httpRestRequestObject = httpRestRequestObject;
    }

    @Override
    public void run() {
        int tries = 0;
        while(!isFinished && tries++ < 3) {
            Optional<GeneralResponse> response = httpRestRequestObject.setQuery(query).get();
            if(response.isPresent()) {
                GeneralResponse generalResponse = response.get();
                if(generalResponse.getType() == ResponseType.SUCCESS) {
                    isFinished = true;
                    log.info(generalResponse.getMessage());
                } else {
                    log.error(generalResponse.getMessage());
                }
            }
        }
    }

    private static final Logger log = LoggerFactory.getLogger(FeedPoller.class);
}
