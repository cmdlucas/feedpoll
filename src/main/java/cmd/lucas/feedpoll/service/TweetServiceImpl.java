package cmd.lucas.feedpoll.service;

import cmd.lucas.feedpoll.model.Tweet;
import cmd.lucas.feedpoll.service.contract.FeedService;
import org.springframework.stereotype.Service;


@Service
public class TweetServiceImpl implements FeedService<Tweet> {
}
