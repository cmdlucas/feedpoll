package cmd.lucas.feedpoll.service.contract;

import java.util.ArrayList;
import java.util.List;

public interface FeedService<T> {
    /**
     * In accordance with the project requirements, thi method fetches
     * the ten most recently stored data in the specific feed's table
     * @return - List<T>
     */
    default List<T> fetchLatestTen() {
        return new ArrayList<>(0);
    }
}
