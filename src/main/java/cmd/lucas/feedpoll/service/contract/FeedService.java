package cmd.lucas.feedpoll.service.contract;

import cmd.lucas.feedpoll.model.Tweet;

import java.util.List;

public interface FeedService {
    List<Tweet> fetchTweets();
}
