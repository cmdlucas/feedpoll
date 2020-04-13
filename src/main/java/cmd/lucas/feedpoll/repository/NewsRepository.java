package cmd.lucas.feedpoll.repository;

import cmd.lucas.feedpoll.dao.contract.NewsDao;
import cmd.lucas.feedpoll.model.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NewsRepository {

    private final NewsDao newsDao;

    @Autowired
    public NewsRepository(NewsDao newsDao) {
        this.newsDao = newsDao;
    }

    public void storeNews(@NonNull List<News> newsList) {
        for(News news : newsList) {
            newsDao.create(news);
        }
    }

    public long totalNews(){
        return newsDao.totalNews();
    }

    public List<News> getSomeNews(int N) {
        return newsDao.getSomeNews(N);
    }
}
