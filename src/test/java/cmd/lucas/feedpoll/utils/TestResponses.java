package cmd.lucas.feedpoll.utils;

import cmd.lucas.feedpoll.model.NewsArticle;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public final class TestResponses {
    public static final String NO_NEWS_ARTICLE_STRING = "[]";

    public static List<NewsArticle> noNewsArticleObject() {
        return new ArrayList<>(0);
    }

    public static final String ONE_NEWS_ARTICLE_STRING = "[{\"id\":1140,\"sourceName\":\"TechCrunch\",\"sourceId\":\"techcrunch\"," +
            "\"author\":\"Anthony Ha\",\"title\":\"Daily Crunch: Trump to propose $850B stimulus\"," +
            "\"description\":\"President Trump is preparing a massive stimulus proposal, Amazon adjusts to COVID-19 and HashiCorp is now valued at more than $5 billion. Here’s your Daily Crunch for March 17, 2020. 1. Trump administration proposes $850B stimulus package to stabilize the eco…\"" +
            ",\"url\":\"http://techcrunch.com/2020/03/17/daily-crunch-trump-to-propose-850b-stimulus/\",\"urlToImage\":\"https://techcrunch.com/wp-content/uploads/2020/03/GettyImages-1207390126.jpeg?w=586\"," +
            "\"publishedAt\":\"2020-03-17T16:36:52Z\",\"storageTimestamp\":1586813201513,\"storageDate\":\"2020-04-14\"}]";

    public static List<NewsArticle> oneNewsArticleList() {
        List<NewsArticle> newsArticles = new ArrayList<>(1);
        NewsArticle newsArticle = new NewsArticle();
        newsArticle.setId(BigInteger.valueOf(1140));
        newsArticle.setSourceId("techcrunch");
        newsArticle.setSourceName("TechCrunch");
        newsArticle.setTitle("Daily Crunch: Trump to propose $850B stimulus");
        newsArticle.setDescription("President Trump is preparing a massive stimulus proposal, Amazon adjusts to COVID-19 and HashiCorp is now valued at more than $5 billion. Here’s your Daily Crunch for March 17, 2020. 1. Trump administration proposes $850B stimulus package to stabilize the eco…");
        newsArticle.setUrl("http://techcrunch.com/2020/03/17/daily-crunch-trump-to-propose-850b-stimulus/");
        newsArticle.setUrlToImage("https://techcrunch.com/wp-content/uploads/2020/03/GettyImages-1207390126.jpeg?w=586");
        newsArticle.setPublishedAt("2020-03-17T16:36:52Z");
        newsArticle.setAuthor("Anthony Ha");
        newsArticle.setStorageDate(LocalDate.now());
        newsArticle.setStorageTimestamp(1586813201513L);
        newsArticles.add(newsArticle);
        return newsArticles;
    }

    public static List<NewsArticle> randomListGenerator(int N) {
        List<NewsArticle> newsArticles = new ArrayList<>(1);
        NewsArticle newsArticle = new NewsArticle();
        newsArticle.setId(BigInteger.valueOf(1140));
        newsArticle.setSourceId("techcrunch");
        newsArticle.setSourceName("TechCrunch");
        newsArticle.setTitle("Daily Crunch: Trump to propose $850B stimulus");
        newsArticle.setDescription("President Trump is preparing a massive stimulus proposal, Amazon adjusts to COVID-19 and HashiCorp is now valued at more than $5 billion. Here’s your Daily Crunch for March 17, 2020. 1. Trump administration proposes $850B stimulus package to stabilize the eco…");
        newsArticle.setUrl("http://techcrunch.com/2020/03/17/daily-crunch-trump-to-propose-850b-stimulus/");
        newsArticle.setUrlToImage("https://techcrunch.com/wp-content/uploads/2020/03/GettyImages-1207390126.jpeg?w=586");
        newsArticle.setPublishedAt("2020-03-17T16:36:52Z");
        newsArticle.setAuthor("Anthony Ha");
        newsArticle.setStorageDate(LocalDate.now());
        newsArticle.setStorageTimestamp(1586813201513L);
        newsArticles.add(newsArticle);
        return newsArticles;
    }

    private TestResponses() {}
}
