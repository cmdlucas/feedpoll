package cmd.lucas.feedpoll.domain.http;


import cmd.lucas.feedpoll.domain.http.contract.HttpRestRequestObject;
import cmd.lucas.feedpoll.domain.service.NewsApi;
import cmd.lucas.feedpoll.model.NewsArticle;
import cmd.lucas.feedpoll.repository.NewsRepository;
import cmd.lucas.feedpoll.util.Urls;
import cmd.lucas.feedpoll.util.apirequest.contract.Query;
import cmd.lucas.feedpoll.util.apirequest.newsapi.EverythingQuery;
import cmd.lucas.feedpoll.util.apiresponse.newsapi.Article;
import cmd.lucas.feedpoll.util.apiresponse.newsapi.NewsData;
import cmd.lucas.feedpoll.util.opsresponse.GeneralResponse;
import cmd.lucas.feedpoll.util.opsresponse.ResponseType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import retrofit2.Response;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class NewsApiRequestEverything implements HttpRestRequestObject {

    private final NewsApi newsApi;
    private final NewsRepository newsRepository;

    private EverythingQuery query;

    @Autowired
    public NewsApiRequestEverything(NewsApi newsApi, NewsRepository newsRepository) {
        this.newsApi = newsApi;
        this.newsRepository = newsRepository;
    }

    @Override
    public HttpRestRequestObject setQuery(Query query) {
        if(query instanceof EverythingQuery) {
            this.query = (EverythingQuery) query;
        }
        return this;
    }

    @Override
    public Optional<GeneralResponse> get() {
        try {
            // undefined request query parameters
            if(this.query == null) {
                String errorMessage = "Cannot initialize HTTP request on " + Urls.NEWS_API_ORG_BASE_URL + " with empty query parameters";
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
            NewsArticle newsArticle = new NewsArticle();
            newsArticle.setSourceId(article.getSource().getId());
            newsArticle.setSourceName(article.getSource().getName());
            newsArticle.setTitle(article.getTitle());
            newsArticle.setDescription(article.getDescription());
            newsArticle.setUrl(article.getUrl());
            newsArticle.setUrlToImage(article.getUrlToImage());
            newsArticle.setPublishedAt(article.getPublishedAt());
            newsArticle.setAuthor(article.getAuthor());
            newsArticle.setStorageDate(LocalDate.now());
            newsArticle.setStorageTimestamp(System.currentTimeMillis());
            newsArticles.add(newsArticle);
        }

        newsRepository.storeNews(newsArticles);
    }
}
