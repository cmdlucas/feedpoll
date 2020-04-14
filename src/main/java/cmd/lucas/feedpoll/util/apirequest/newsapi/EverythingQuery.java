package cmd.lucas.feedpoll.util.apirequest.newsapi;

import cmd.lucas.feedpoll.util.apirequest.contract.Query;

public class EverythingQuery implements Query {
    private final String apiKey;
    private final String searchQuery;
    private final int pageSize;
    private final int page;

    public EverythingQuery(String apiKey, String searchQuery, int pageSize, int page) {
        this.apiKey = apiKey;
        this.searchQuery = searchQuery;
        this.pageSize = pageSize;
        this.page = page;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getSearchQuery() {
        return searchQuery;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getPage() {
        return page;
    }
}
