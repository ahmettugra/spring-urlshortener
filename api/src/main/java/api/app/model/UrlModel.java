package api.app.model;

/**
 * Created by ahmettugra on 3.08.2017.
 */
public class UrlModel {
    private String shortUrl;
    private String LongUrl;
    private int id;

    public UrlModel(String s, int urlID) {
    }

    public UrlModel(String shortUrl, String longUrl, int id) {
        this.shortUrl = shortUrl;
        LongUrl = longUrl;
        this.id = id;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getLongUrl() {
        return LongUrl;
    }

    public void setLongUrl(String longUrl) {
        LongUrl = longUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
