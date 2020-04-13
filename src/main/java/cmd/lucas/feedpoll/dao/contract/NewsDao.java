package cmd.lucas.feedpoll.dao.contract;

import cmd.lucas.feedpoll.model.News;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Map;

public interface NewsDao {
    /**
     * An instance of the JdbcTemplate
     * @return - JdbcTemplate
     */
    JdbcTemplate jdbcTemplate();

    /**
     * Store a list of news into the database
     * @param news - single news entity to be stored in the database
     */
    default void create(@NonNull News news) {
        String sqlStatement = "INSERT INTO news (id, timestamp, storage_date) VALUES (?,?,?)";
        jdbcTemplate().update(sqlStatement, news.getId(), news.getTimestamp(), news.getStorageDate());
    }

    /**
     * Store a list of news into the database
     * @param news - single news entity to be updated in the database
     */
    default void update(@NonNull News news) {

    }

    /**
     * Delete news from the database
     * @param id - id of the news to be deleted from the database
     */
    default void delete(int id) {

    }

    /**
     * Find news in the database
     * @param id - id the news to be retrieved in the databased
     */
    default void findById(int id) {

    }

    /**
     * Counts the total number of news that have been stored
     * @return - long
     */
    default long totalNews() {
        String sqlStatement = "SELECT COUNT(id) AS counter FROM news";
        Map<String, Object> data = jdbcTemplate().queryForMap(sqlStatement);
        return (Long) data.get("counter");
    }

    /**
     * Build a list of all the news in the database
     * @return - List<News>
     */
    default List<News> getAllNews() {
        return null;
    }

    /**
     * Builds a list of max size N, of news in descending order of storage date
     * @param N - total number of news to be returned
     * @return - List<News>
     */
    default List<News> getSomeNews(int N) {
        String sqlStatement = "SELECT * FROM news ORDER BY timestamp, id DESC LIMIT ?";
        return jdbcTemplate().query(sqlStatement, new BeanPropertyRowMapper<>(News.class), N);
    }

    /**
     * Builds a list of news that have been stored after the origin tweet
     * The default list is sorted in the descending order of storage date
     * NB: The tweet with originNewsId will not be included in the result
     * @param originNewsId - the base tweet id from whence to begin our search
     * @param max - maximum size of list to be returned
     * @return - List<News>
     */
    default List<News> getSomeNews(String originNewsId, int max) {
        return null;
    }
}
