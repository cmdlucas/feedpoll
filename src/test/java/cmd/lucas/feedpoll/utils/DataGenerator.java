package cmd.lucas.feedpoll.utils;

import cmd.lucas.feedpoll.domain.model.NewsArticle;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public final class DataGenerator {

    public static NewsArticle exampleNewsArticle() {
        return new NewsArticle(
                BigInteger.valueOf(1140),
                "techcrunch",
                "TechCrunch",
                "Anthony Ha",
                "Daily Crunch: Trump to propose $850B stimulus",
                "President Trump is preparing a massive stimulus proposal, Amazon adjusts to COVID-19 and HashiCorp is now valued at more than $5 billion. Here’s your Daily Crunch for March 17, 2020. 1. Trump administration proposes $850B stimulus package to stabilize the eco…",
                "http://techcrunch.com/2020/03/17/daily-crunch-trump-to-propose-850b-stimulus/",
                "https://techcrunch.com/wp-content/uploads/2020/03/GettyImages-1207390126.jpeg?w=586",
                "2020-03-17T16:36:52Z",
                Timestamp.valueOf(LocalDateTime.of(2020, 4, 14, 0, 0))
        );
    }

    public static List<NewsArticle> newsArticles(int N) {
        List<NewsArticle> newsArticles = new ArrayList<>(N);

        for(int i = 0; i < N; i++) {
            NewsArticle newsArticle = DataGenerator.exampleNewsArticle();
            newsArticles.add(newsArticle);
        }

        return newsArticles;
    }

    private DataGenerator(){}
}
