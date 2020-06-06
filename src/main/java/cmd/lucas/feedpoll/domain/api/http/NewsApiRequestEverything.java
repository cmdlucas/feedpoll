package cmd.lucas.feedpoll.domain.api.http;


import cmd.lucas.feedpoll.config.qualifier.NewsApiBaseUrl;
import cmd.lucas.feedpoll.dao.contract.NewsDao;
import cmd.lucas.feedpoll.domain.api.request.NewsApi;
import cmd.lucas.feedpoll.domain.model.NewsArticle;
import cmd.lucas.feedpoll.domain.api.request.Query;
import cmd.lucas.feedpoll.domain.api.request.EverythingQuery;
import cmd.lucas.feedpoll.domain.api.response.newsapi.Article;
import cmd.lucas.feedpoll.domain.api.response.newsapi.NewsData;
import cmd.lucas.feedpoll.util.opsresponse.GeneralResponse;
import cmd.lucas.feedpoll.util.opsresponse.ResponseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import retrofit2.Response;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class NewsApiRequestEverything implements HttpRestRequestObject {

    private final NewsApi newsApi;
    private final NewsDao newsDao;

    private EverythingQuery query;

    @NewsApiBaseUrl
    private String baseUrl;

    @Autowired
    public NewsApiRequestEverything(NewsApi newsApi, NewsDao newsDao) {
        this.newsApi = newsApi;
        this.newsDao = newsDao;
    }

    @Override
    public HttpRestRequestObject setQuery(Query query) {
        if(query instanceof EverythingQuery) {
            this.query = (EverythingQuery) query;
        }
        return this;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Optional<GeneralResponse> get() {
        try {
            // undefined request query parameters
            if(this.query == null) {
                String errorMessage = "Cannot initialize HTTP request on " + baseUrl + " with empty query parameters";
                throw new Exception(errorMessage);
            }

            // call the everything api
            Response<NewsData> response = newsApi
                    .everything(query.getApiKey(), query.getSearchQuery(), query.getPageSize(), query.getPage())
                    .execute();

            // Unsuccessful request to the API service
            if(!response.isSuccessful() || response.body() == null) {
                String errorMessage = "FAILED: " + response.raw();
                throw new Exception(errorMessage);
            }

            // Operational error from the API service
            if(!response.body().getStatus().equals("ok")) {
                String errorMessage = "FAILED: " + response.body().getMessage();
                throw new Exception(errorMessage);
            }

            // persist in our DB
            storeInDB(response.body());

            return Optional.of(new GeneralResponse(ResponseType.SUCCESS, "Data successfully fetched and persisted."));
        } catch (Exception e) {
            return Optional.of(new GeneralResponse(ResponseType.FAILED, e.getMessage()));
        }
    }

    private void storeInDB(NewsData newsData) throws RuntimeException {
        List<NewsArticle> newsArticles = new ArrayList<>(newsData.getArticles().size());

        for(Article article : newsData.getArticles()) {
            newsArticles.add(
                    new NewsArticle(
                            null,
                            article.getSource().getId(),
                            article.getSource().getName(),
                            article.getAuthor(),
                            article.getTitle(),
                            article.getDescription(),
                            article.getUrl(),
                            article.getUrlToImage(),
                            article.getPublishedAt(),
                            Timestamp.valueOf(LocalDateTime.now())
                    )
            );
        }

        newsDao.create(newsArticles);
    }
}
