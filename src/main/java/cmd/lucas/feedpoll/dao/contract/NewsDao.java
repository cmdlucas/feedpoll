package cmd.lucas.feedpoll.dao.contract;

import cmd.lucas.feedpoll.domain.model.NewsArticle;
import cmd.lucas.feedpoll.domain.dto.NewsArticleDto;
import org.springframework.lang.NonNull;

import java.util.List;

public interface NewsDao {
    /**
     * Store a list of news into the database
     * @param newsArticles - news entities to be stored in the database
     */
    void create(@NonNull List<NewsArticle> newsArticles);

    /**
     * Store a list of news into the database
     * @param newsArticles - news entities to be updated in the database
     */
    void update(@NonNull List<NewsArticle> newsArticles);

    /**
     * Counts the total number of news that have been stored
     * @return - long
     */
    long countNewsArticle();

    /**
     * Builds a list of news in descending order of storage date
     * @param size - total number of news to return
     * @return - List<NewsArticle>
     */
    List<NewsArticleDto> getSomeNews(int size);

    /**
     * Empty the table
     */
    void truncate();

}
