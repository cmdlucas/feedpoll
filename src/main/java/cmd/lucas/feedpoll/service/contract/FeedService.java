package cmd.lucas.feedpoll.service.contract;

import java.util.ArrayList;
import java.util.List;

public interface FeedService<T> {
    /**
     * In accordance with the project requirements, this method fetches
     * the ten most recently stored data in the specified feed's table
     * @return - List<T>
     */
    default List<T> fetchLatestTen() {
        return new ArrayList<>(0);
    }
}
