package cmd.lucas.feedpoll.service.contract;

import java.util.ArrayList;
import java.util.List;

public interface FeedService<T> {
    /**
     * Based on the requirements, this method should be overridden
     * to fetch the ten most recently stored data in the specific feed's table
     * @return - List<T>
     */
    default List<T> fetchLatestTen() {
        return new ArrayList<>(0);
    }
}
