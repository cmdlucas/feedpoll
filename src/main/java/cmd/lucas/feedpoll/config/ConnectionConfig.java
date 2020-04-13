package cmd.lucas.feedpoll.config;

import cmd.lucas.feedpoll.qualifier.NewsApiConnection;
import cmd.lucas.feedpoll.util.Urls;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

@Configuration
@ComponentScan(basePackages = "cmd.lucas.feedpoll")
public class ConnectionConfig {

    @Bean
    public Gson gson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    @Bean
    Interceptor requestInterceptor(){
        return chain -> {
            Request request = chain.request().newBuilder()
                    .addHeader("Accept", "application/json").build();
            return chain.proceed(request);
        };
    }

    @Bean
    HttpLoggingInterceptor httpLoggingInterceptor(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(message -> {});
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

    @Bean
    @Autowired
    GsonConverterFactory gsonConverterFactory(Gson gson){
        return GsonConverterFactory.create(gson);
    }

    @Bean
    @Autowired
    OkHttpClient okHttpClient(Interceptor requestInterceptor, HttpLoggingInterceptor httpLoggingInterceptor){
        OkHttpClient.Builder httpClient = new OkHttpClient().newBuilder();
        return httpClient
                .addInterceptor(requestInterceptor)
                .addInterceptor(httpLoggingInterceptor)
                .readTimeout(360, TimeUnit.SECONDS)
                .connectTimeout(360, TimeUnit.SECONDS)
                .build();
    }

    @Bean
    @Autowired
    @NewsApiConnection
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    Retrofit newsApiConnection(OkHttpClient okHttpClient, GsonConverterFactory gsonConverterFactory){
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Urls.NEWS_API_ORG_BASE_URL)
                .addConverterFactory(gsonConverterFactory)
                .build();
    }
}
