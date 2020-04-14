package cmd.lucas.feedpoll.domain.service;

import cmd.lucas.feedpoll.util.ApiEndpoints;
import cmd.lucas.feedpoll.util.apiresponse.newsapi.NewsData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApi {

    @GET(ApiEndpoints.NEWS_API_EVERYTHING)
    Call<NewsData> everything(
            @Query("apiKey") String apiKey,
            @Query("q") String searchQuery,
            @Query("pageSize") int pageSize,
            @Query("page") int page
    );

    @GET(ApiEndpoints.NEWS_API_EVERYTHING)
    Call<NewsData> everythingFromDate(
            @Query("apiKey") String apiKey,
            @Query("q") String searchQuery,
            @Query("pageSize") int pageSize,
            @Query("page") int page,
            @Query("from") String fromDate
    );

    @GET(ApiEndpoints.NEWS_API_EVERYTHING)
    Call<NewsData> everythingWithinDateRange(
            @Query("apiKey") String apiKey,
            @Query("q") String searchQuery,
            @Query("pageSize") int pageSize,
            @Query("page") int page,
            @Query("from") String fromDate,
            @Query("to") String toDate
    );

    @GET(ApiEndpoints.NEWS_API_TOP_HEADLINES)
    Call<NewsData> topHeadlines(
            @Query("apiKey") String apiKey,
            @Query("pageSize") int pageSize,
            @Query("page") int page
    );

    @GET(ApiEndpoints.NEWS_API_TOP_HEADLINES)
    Call<NewsData> topHeadlinesInCountry(
            @Query("apiKey") String apiKey,
            @Query("pageSize") int pageSize,
            @Query("page") int page,
            @Query("country") String country
    );
}