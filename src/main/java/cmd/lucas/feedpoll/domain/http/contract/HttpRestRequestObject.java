package cmd.lucas.feedpoll.domain.http.contract;

import cmd.lucas.feedpoll.util.apirequest.contract.Query;
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
    default Optional<GeneralResponse> get() { return Optional.empty(); }

    /**
     * Make a POST http request
     */
    default Optional<GeneralResponse> post() { return Optional.empty(); }
}
