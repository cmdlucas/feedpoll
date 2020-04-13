package cmd.lucas.feedpoll.dao.contract;

import cmd.lucas.feedpoll.model.NewsArticle;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.lang.NonNull;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;

public interface NewsDao {
    /**
     * An instance of the JdbcTemplate to basically help us manage connections
     * @return - JdbcTemplate
     */
    JdbcTemplate jdbcTemplate();

    /**
     * Store a list of news into the database
     * @param newsArticle - single news entity to be stored in the database
     */
    default void create(@NonNull NewsArticle newsArticle) {
        jdbcTemplate().update(connection -> {

            String sqlStatement = "INSERT INTO news_article " +
                    "(source_id, source_name, author, title, description, url, url_to_image, published_at, storage_timestamp, storage_date) " +
                    "VALUES (?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setString(1, newsArticle.getSourceId());
            preparedStatement.setString(2, newsArticle.getSourceName());
            preparedStatement.setString(3, newsArticle.getAuthor());
            preparedStatement.setString(4, newsArticle.getTitle());
            preparedStatement.setString(5, newsArticle.getDescription());
            preparedStatement.setString(6, newsArticle.getUrl());
            preparedStatement.setString(7, newsArticle.getUrlToImage());
            preparedStatement.setString(8, newsArticle.getPublishedAt());
            preparedStatement.setLong(9, newsArticle.getStorageTimestamp());
            preparedStatement.setString(10, newsArticle.getStorageDate().toString());

            return preparedStatement;
        });
    }

    /**
     * Store a list of news into the database
     * @param newsArticle - single news entity to be updated in the database
     */
    default void update(@NonNull NewsArticle newsArticle) {
        jdbcTemplate().update(connection -> {

            String sqlStatement = "UPDATE news_article " +
                    "SET source_id=?, source_name=?, author=?, title=?, description=?, url=?, url_to_image=?, " +
                    "published_at=?, storage_timestamp=?, storage_date=?";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setString(1, newsArticle.getSourceId());
            preparedStatement.setString(2, newsArticle.getSourceName());
            preparedStatement.setString(3, newsArticle.getAuthor());
            preparedStatement.setString(4, newsArticle.getTitle());
            preparedStatement.setString(5, newsArticle.getDescription());
            preparedStatement.setString(6, newsArticle.getUrl());
            preparedStatement.setString(7, newsArticle.getUrlToImage());
            preparedStatement.setString(8, newsArticle.getPublishedAt());
            preparedStatement.setLong(9, newsArticle.getStorageTimestamp());
            preparedStatement.setString(10, newsArticle.getStorageDate().toString());

            return preparedStatement;
        });

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
        String sqlStatement = "SELECT COUNT(id) AS counter FROM news_article";
        Map<String, Object> data = jdbcTemplate().queryForMap(sqlStatement);
        return (Long) data.get("counter");
    }

    /**
     * Build a list of all the news in the database
     * @return - List<News>
     */
    default List<NewsArticle> getAllNews() {
        return null;
    }

    /**
     * Builds a list of max size N, of news in descending order of storage date
     * @param N - total number of news to be returned
     * @return - List<News>
     */
    default List<NewsArticle> getSomeNews(int N) {
        return jdbcTemplate().query(connection -> {
            String sqlStatement = "SELECT * FROM news_article ORDER BY storage_timestamp DESC, id DESC LIMIT ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setInt(1, N);

            return preparedStatement;
        }, new BeanPropertyRowMapper<>(NewsArticle.class));
    }

    /**
     * Builds a list of news that have been stored after the origin tweet
     * The default list is sorted in the descending order of storage date
     * NB: The tweet with originNewsId will not be included in the result
     * @param originNewsId - the base tweet id from whence to begin our search
     * @param max - maximum size of list to be returned
     * @return - List<News>
     */
    default List<NewsArticle> getSomeNews(String originNewsId, int max) {
        return null;
    }
}
