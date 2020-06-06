package cmd.lucas.feedpoll.controller;

import cmd.lucas.feedpoll.domain.dto.NewsArticleDto;
import cmd.lucas.feedpoll.service.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestApiController {

    @Value("${app.hello.message}")
    private String helloMessage;

    private final FeedService<NewsArticleDto> newsFeedService;

    @Autowired
    public RestApiController(FeedService<NewsArticleDto> newsFeedService) {
        this.newsFeedService = newsFeedService;
    }

    @GetMapping(Mappings.NEWS)
    public List<NewsArticleDto> news() {
        return newsFeedService.fetchLatestTen();
    }

    @ResponseBody
    @RequestMapping("/")
    public String home() {
        return helloMessage;
    }
}
