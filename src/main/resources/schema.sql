/**
 * CREATE NEWS TABLE
 */
CREATE TABLE IF NOT EXISTS news_article (
    id BIGINT IDENTITY (1, 1) PRIMARY KEY,
    source_id VARCHAR(191),
    source_name VARCHAR(191) NOT NULL,
    author VARCHAR(191),
    title TEXT,
    description LONGTEXT,
    url TEXT,
    url_to_image TEXT,
    published_at VARCHAR(191),
    storage_date TIMESTAMP NOT NULL
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;