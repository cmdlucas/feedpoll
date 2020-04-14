package cmd.lucas.feedpoll.controller;

import cmd.lucas.feedpoll.model.NewsArticle;
import cmd.lucas.feedpoll.service.contract.FeedService;
import cmd.lucas.feedpoll.util.Mappings;
import cmd.lucas.feedpoll.util.Responses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestApiController {

    private final FeedService<NewsArticle> newsFeedService;

    @Autowired
    public RestApiController(FeedService<NewsArticle> newsFeedService) {
        this.newsFeedService = newsFeedService;
    }

    @GetMapping(Mappings.NEWS)
    public List<NewsArticle> news() {
        return newsFeedService.fetchLatestTen();
    }

    @ResponseBody
    @RequestMapping("/")
    public String home() {
        return Responses.LANDING_PAGE;
    }
}
