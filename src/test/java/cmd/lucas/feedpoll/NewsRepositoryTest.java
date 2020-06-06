package cmd.lucas.feedpoll;

import cmd.lucas.feedpoll.dao.contract.NewsDao;
import cmd.lucas.feedpoll.domain.model.NewsArticle;
import cmd.lucas.feedpoll.utils.DataGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class NewsRepositoryTest {

    @Autowired
    private NewsDao newsDao;

    @BeforeEach
    private void emptyTable() {
        newsDao.truncate();
    }

    @Test
    void shouldReturnEmptyListIfNothingWasStored() {
        assertThat(newsDao.getSomeNews(10).size()).isEqualTo(0);
    }

    @Test
    void shouldStoreNewsArticlesSuccessfully() {
        newsDao.create(DataGenerator.newsArticles(1));
        assertThat(newsDao.countNewsArticle()).isEqualTo(1);
    }

    @Test
    void shouldReturnLatestNewsInTheirDescOrderIfSomethingWasStored() {
        List<NewsArticle> mockNewsArticles = DataGenerator.newsArticles(10);
        newsDao.create(mockNewsArticles);
        assertThat(newsDao.getSomeNews(10).get(0).getAuthor())
                .isEqualTo(mockNewsArticles.get(9).getAuthor());
    }
}
