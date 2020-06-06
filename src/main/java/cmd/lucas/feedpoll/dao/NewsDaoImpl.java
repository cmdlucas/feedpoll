package cmd.lucas.feedpoll.dao;

import cmd.lucas.feedpoll.dao.contract.NewsDao;
import cmd.lucas.feedpoll.domain.model.NewsArticle;
import cmd.lucas.feedpoll.domain.dto.NewsArticleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;

@Component
public class NewsDaoImpl implements NewsDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public NewsDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void create(@NonNull List<NewsArticle> newsArticles) {
        String sqlStatement = "INSERT INTO news_article " +
                "(source_id, source_name, author, title, description, url, url_to_image, published_at, storage_date) " +
                "VALUES (?,?,?,?,?,?,?,?,?)";
        jdbcUpdate(newsArticles, sqlStatement);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void update(@NonNull List<NewsArticle> newsArticles) {
        String sqlStatement = "UPDATE news_article " +
                "SET source_id=?, source_name=?, author=?, title=?, description=?, url=?, url_to_image=?, " +
                "published_at=?, storage_date=?";
        jdbcUpdate(newsArticles, sqlStatement);
    }

    /**
     * Create or Update a NewsArticle in the DB
     * @param newsArticles - news articles
     * @param sqlStatement - a sql query
     */
    private void jdbcUpdate(@NonNull List<NewsArticle> newsArticles, @NonNull String sqlStatement) {
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            connection.setAutoCommit(false);
            for(NewsArticle newsArticle: newsArticles) {
                preparedStatement.setString(1, newsArticle.getSourceId());
                preparedStatement.setString(2, newsArticle.getSourceName());
                preparedStatement.setString(3, newsArticle.getAuthor());
                preparedStatement.setString(4, newsArticle.getTitle());
                preparedStatement.setString(5, newsArticle.getDescription());
                preparedStatement.setString(6, newsArticle.getUrl());
                preparedStatement.setString(7, newsArticle.getUrlToImage());
                preparedStatement.setString(8, newsArticle.getPublishedAt());
                preparedStatement.setTimestamp(9, newsArticle.getStorageDate());
                preparedStatement.executeUpdate();
            }
            connection.commit();
            return preparedStatement;
        });
    }

    /**
     * @inheritDoc
     */
    @Override
    public long countNewsArticle() {
        String sqlStatement = "SELECT COUNT(id) AS counter FROM news_article";
        Map<String, Object> data = jdbcTemplate.queryForMap(sqlStatement);
        return (Long) data.get("counter");
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<NewsArticleDto> getSomeNews(int size) {
        return jdbcTemplate.query(connection -> {
            String sqlStatement = "SELECT * FROM news_article ORDER BY storage_date DESC, id DESC LIMIT ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            preparedStatement.setInt(1, size);

            return preparedStatement;
        }, new BeanPropertyRowMapper<>(NewsArticleDto.class));
    }

    /**
     * @inheritDoc
     */
    @Override
    public void truncate() {
        String sqlStatement = "TRUNCATE TABLE news_article";
        jdbcTemplate.execute(sqlStatement);
    }
}
