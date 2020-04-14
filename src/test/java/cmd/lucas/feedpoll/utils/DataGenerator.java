package cmd.lucas.feedpoll.utils;

import cmd.lucas.feedpoll.model.NewsArticle;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public final class DataGenerator {

    public static List<NewsArticle> newsArticles(int N) {
        List<NewsArticle> newsArticles = new ArrayList<>(N);

        for(int i = 0; i < N; i++) {
            NewsArticle newsArticle = new NewsArticle();
            newsArticle.setId(BigInteger.valueOf(i + 1));
            newsArticle.setSourceId("techcrunch");
            newsArticle.setSourceName("TechCrunch");
            newsArticle.setTitle("Daily Crunch: Trump to propose $850B stimulus");
            newsArticle.setDescription("President Trump is preparing a massive stimulus proposal, Amazon adjusts to COVID-19 and HashiCorp is now valued at more than $5 billion. Here’s your Daily Crunch for March 17, 2020. 1. Trump administration proposes $850B stimulus package to stabilize the eco…");
            newsArticle.setUrl("http://techcrunch.com/2020/03/17/daily-crunch-trump-to-propose-850b-stimulus/");
            newsArticle.setUrlToImage("https://techcrunch.com/wp-content/uploads/2020/03/GettyImages-1207390126.jpeg?w=586");
            newsArticle.setPublishedAt("2020-03-17T16:36:52Z");
            newsArticle.setAuthor("Anthony Ha");
            newsArticle.setStorageDate(LocalDate.now());
            newsArticle.setStorageTimestamp(System.currentTimeMillis());
            newsArticles.add(newsArticle);
        }

        return newsArticles;
    }

    private DataGenerator(){}
}
