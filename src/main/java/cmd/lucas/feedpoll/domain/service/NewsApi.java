package cmd.lucas.feedpoll.domain.service;

import cmd.lucas.feedpoll.util.apiresponse.ApiEndpoints;
import cmd.lucas.feedpoll.util.apirespons.newsapi.NewsData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApi {

    @GET(ApiEndpoints.NEWS_API_EVERYTHING)
    Call<NewsData> everything(
            @Query("apiKey") String apiKey,
            @Query("q") String searchQuery,
            @Query("pageSize") String pageSize,
            @Query("page") String page
    );

    @GET(ApiEndpoints.NEWS_API_EVERYTHING)
    Call<NewsData> everythingFromDate(
            @Query("apiKey") String apiKey,
            @Query("q") String searchQuery,
            @Query("pageSize") String pageSize,
            @Query("page") String page,
            @Query("from") String fromDate
    );

    @GET(ApiEndpoints.NEWS_API_EVERYTHING)
    Call<NewsData> everythingWithinDateRange(
            @Query("apiKey") String apiKey,
            @Query("q") String searchQuery,
            @Query("pageSize") String pageSize,
            @Query("page") String page,
            @Query("from") String fromDate,
            @Query("to") String toDate
    );

    @GET(ApiEndpoints.NEWS_API_TOP_HEADLINES)
    Call<NewsData> topHeadlines(
            @Query("apiKey") String apiKey,
            @Query("pageSize") String pageSize,
            @Query("page") String page
    );

    @GET(ApiEndpoints.NEWS_API_TOP_HEADLINES)
    Call<NewsData> topHeadlinesInCountry(
            @Query("apiKey") String apiKey,
            @Query("pageSize") String pageSize,
            @Query("page") String page,
            @Query("country") String country
    );
}