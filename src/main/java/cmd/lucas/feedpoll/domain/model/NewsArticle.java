package cmd.lucas.feedpoll.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigInteger;
import java.sql.Timestamp;

@Getter
@AllArgsConstructor
public class NewsArticle {
    private final BigInteger id;
    private final String sourceId;
    private final String sourceName;
    private final String author;
    private final String title;
    private final String description;
    private final String url;
    private final String urlToImage;
    private final String publishedAt;
    private final Timestamp storageDate;
}
