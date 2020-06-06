package cmd.lucas.feedpoll.service;

import cmd.lucas.feedpoll.dao.contract.NewsDao;
import cmd.lucas.feedpoll.domain.dto.NewsArticleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsArticleService implements FeedService<NewsArticleDto> {

    private final NewsDao newsDao;

    @Autowired
    public NewsArticleService(NewsDao newsDao) {
        this.newsDao = newsDao;
    }

    @Override
    public List<NewsArticleDto> fetchLatestTen() {
        return newsDao.getSomeNews(10);
    }
}
