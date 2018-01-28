package model;

public class NewsContent {
    private final String url;
    private final String title;

    public NewsContent(String url, String title) {
        this.url = url;
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }
}
