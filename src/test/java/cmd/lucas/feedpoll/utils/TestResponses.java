package cmd.lucas.feedpoll.utils;

import cmd.lucas.feedpoll.domain.dto.NewsArticleDto;
import cmd.lucas.feedpoll.domain.model.NewsArticle;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public final class TestResponses {
    public static final String NO_NEWS_ARTICLE_STRING = "[]";

    public static List<NewsArticleDto> noNewsArticleObject() {
        return new ArrayList<>(0);
    }

    public static final String ONE_NEWS_ARTICLE_STRING = "[{\"sourceId\":\"techcrunch\",\"sourceName\":\"TechCrunch\"," +
            "\"author\":\"Anthony Ha\",\"title\":\"Daily Crunch: Trump to propose $850B stimulus\"," +
            "\"description\":\"President Trump is preparing a massive stimulus proposal, Amazon adjusts to COVID-19 and HashiCorp is now valued at more than $5 billion. Here’s your Daily Crunch for March 17, 2020. 1. Trump administration proposes $850B stimulus package to stabilize the eco…\"" +
            ",\"url\":\"http://techcrunch.com/2020/03/17/daily-crunch-trump-to-propose-850b-stimulus/\",\"urlToImage\":\"https://techcrunch.com/wp-content/uploads/2020/03/GettyImages-1207390126.jpeg?w=586\"," +
            "\"publishedAt\":\"2020-03-17T16:36:52Z\"}]";

    public static List<NewsArticleDto> oneNewsArticleList() {
        List<NewsArticleDto> newsArticles = new ArrayList<>(1);
        NewsArticle newsArticle = DataGenerator.exampleNewsArticle();
        newsArticles.add(
                new NewsArticleDto(
                        newsArticle.getSourceId(),
                        newsArticle.getSourceName(),
                        newsArticle.getAuthor(),
                        newsArticle.getTitle(),
                        newsArticle.getDescription(),
                        newsArticle.getUrl(),
                        newsArticle.getUrlToImage(),
                        newsArticle.getPublishedAt()
                )
        );
        return newsArticles;
    }

    private TestResponses() {}
}
