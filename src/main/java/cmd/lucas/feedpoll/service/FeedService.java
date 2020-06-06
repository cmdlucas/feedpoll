package cmd.lucas.feedpoll.service;

import java.util.List;

public interface FeedService<T> {
    /**
     * In accordance with the project requirements, this method fetches
     * the ten most recently stored data in the specified feed's table
     * @return - List<T>
     */
    List<T> fetchLatestTen();
}
