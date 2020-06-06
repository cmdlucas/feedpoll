package cmd.lucas.feedpoll.domain.api.http;

import cmd.lucas.feedpoll.domain.api.request.Query;
import cmd.lucas.feedpoll.util.opsresponse.GeneralResponse;

import java.util.Optional;

public interface HttpRestRequestObject {

    /**
     * Set the query object parameters
     */
    HttpRestRequestObject setQuery(Query query);

    /**
     * Make a GET http request
     */
    Optional<GeneralResponse> get();
}
