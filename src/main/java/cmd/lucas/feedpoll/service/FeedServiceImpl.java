package cmd.lucas.feedpoll.service;

import cmd.lucas.feedpoll.model.Tweet;
import cmd.lucas.feedpoll.repository.TweetRepository;
import cmd.lucas.feedpoll.service.contract.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedServiceImpl implements FeedService {
    private final TweetRepository tweetRepository;

    @Autowired
    public FeedServiceImpl(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }

    @Override
    public List<Tweet> fetchTweets() {
        return tweetRepository.getAllTweets();
    }
}
