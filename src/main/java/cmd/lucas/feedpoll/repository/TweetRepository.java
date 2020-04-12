package cmd.lucas.feedpoll.repository;

import cmd.lucas.feedpoll.model.Tweet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TweetRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TweetRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    /**
     * Store a list of tweets into the database
     * @param tweets
     */
    public void storeTweets(List<Tweet> tweets) {

    }

    /**
     * Counts the total number of tweets that have been stored
     * @return - int
     */
    public int count(){
        return 0;
    }

    /**
     * Build a list of all the tweets in the database
     * @return - List<Tweet>
     */
    public List<Tweet> getAllTweets() {
        return null;
    }

    /**
     * Builds a list of max size, N, of recently stored tweets
     * @param N - total number of tweets to be returned
     * @return - List<Tweet>
     */
    public List<Tweet> getSomeTweets(int N) {
        return null;
    }

    /**
     * Builds a list of tweets that have been stored after the origin tweet
     * NB: The tweet with originTweetId will not be included in the result
     * @param originTweetId - the base tweet id from whence to begin our search
     * @param max - maximum size of list to be returned
     * @return - List<Tweet>
     */
    public List<Tweet> getSomeTweets(String originTweetId, int max) {
        return null;
    }
}
