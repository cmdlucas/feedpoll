package cmd.lucas.feedpoll.controller;

import cmd.lucas.feedpoll.model.News;
import cmd.lucas.feedpoll.model.Tweet;
import cmd.lucas.feedpoll.service.contract.FeedService;
import cmd.lucas.feedpoll.util.apiresponse.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WebApiController {

    private final FeedService<News> newsFeedService;
    private final FeedService<Tweet> tweetFeedService;

    @Autowired
    public WebApiController(FeedService<News> newsFeedService, FeedService<Tweet> tweetFeedService) {
        this.newsFeedService = newsFeedService;
        this.tweetFeedService = tweetFeedService;
    }

    @GetMapping(Mappings.NEWS)
    public List<News> news() {
        return newsFeedService.fetchLatestTen();
    }

    @GetMapping(Mappings.TWEETS)
    public List<Tweet> tweets() {
        return tweetFeedService.fetchLatestTen();
    }
}
