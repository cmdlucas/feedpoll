package cmd.lucas.feedpoll.service;

import cmd.lucas.feedpoll.model.NewsArticle;
import cmd.lucas.feedpoll.repository.NewsRepository;
import cmd.lucas.feedpoll.service.contract.FeedService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements FeedService<NewsArticle> {

    private final NewsRepository newsRepository;

    public NewsServiceImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public List<NewsArticle> fetchLatestTen() {
        return newsRepository.getSomeNews(10);
    }
}
