package web.app.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by ahmettugra on 9.08.2017.
 */
public class UrlModel {

    @NotNull
    @Size(min = 1,max = 50)
    private String shortCode;

    @NotNull
    @Size(min = 5,max = 150)
    private String longUrl;

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }


    @Override
    public String toString() {
        return "UrlModel{" +
                "shortCode='" + shortCode + '\'' +
                ", longUrl='" + longUrl + '\'' +
                '}';
    }
}
