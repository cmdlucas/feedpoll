package cmd.lucas.feedpoll.domain.api.request;

public class EverythingQuery implements Query {
    private final String apiKey;
    private final String searchQuery;
    private final int pageSize;

    public EverythingQuery(String apiKey, String searchQuery, int pageSize) {
        this.apiKey = apiKey;
        this.searchQuery = searchQuery;
        this.pageSize = pageSize;
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
        return 1;
    }
}
