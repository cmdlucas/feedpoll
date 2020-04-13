package cmd.lucas.feedpoll.repository;

import cmd.lucas.feedpoll.dao.contract.NewsDao;
import cmd.lucas.feedpoll.model.NewsArticle;
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

    public void storeNews(@NonNull List<NewsArticle> newsArticleList) {
        for(NewsArticle newsArticle : newsArticleList) {
            newsDao.create(newsArticle);
        }
    }

    public long totalNews(){
        return newsDao.totalNews();
    }

    public List<NewsArticle> getSomeNews(int N) {
        return newsDao.getSomeNews(N);
    }
}
