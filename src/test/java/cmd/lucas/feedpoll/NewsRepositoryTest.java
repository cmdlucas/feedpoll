package cmd.lucas.feedpoll;

import cmd.lucas.feedpoll.dao.contract.NewsDao;
import cmd.lucas.feedpoll.model.NewsArticle;
import cmd.lucas.feedpoll.repository.NewsRepository;
import cmd.lucas.feedpoll.utils.DataGenerator;
import cmd.lucas.feedpoll.utils.TestResponses;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class NewsRepositoryTest {

    @Autowired
    private NewsRepository newsRepository;

    @BeforeEach
    private void emptyTable() {
        newsRepository.emptyNewsArticleTable();
    }

    @Test
    void shouldReturnEmptyListIfNothingWasStored() {
        assertThat(newsRepository.getSomeNews(10).size()).isEqualTo(0);
    }

    @Test
    void shouldStoreNewsArticlesSuccessfully() {
        newsRepository.storeNews(TestResponses.oneNewsArticleList());
        assertThat(newsRepository.countNewsArticles()).isEqualTo(1);
    }

    @Test
    void shouldReturnLatestNewsInTheirDescOrderIfSomethingWasStored() {
        List<NewsArticle> mockNewsArticles = DataGenerator.newsArticles(10);
        newsRepository.storeNews(mockNewsArticles);
        assertThat(newsRepository.getSomeNews(10).get(0).getId())
                .isEqualTo(mockNewsArticles.get(9).getId());
    }
}
