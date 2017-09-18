package data.sql.entities;



import javax.persistence.*;
import java.util.Date;


/**
 * Created by ahmettugra on 3.08.2017.
 */


@Entity
@Table(name = "Urls")
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ShortCode", unique = true,length = 50,nullable = false)
    private String shortCode;

    @Column(name = "LongUrl",length = 150,nullable = false)
    private String longUrl;

    @Column(name = "Created")
    private Date created;

    //public Url(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public boolean isModelValid(){
        if(this.longUrl == null || this.longUrl == "") return false;
        //if(this.shortCode == null || this.shortCode == "") return false;

        return true;
    }

    @Override
    public String toString() {
        return "Url{" +
                "id=" + id +
                ", ShorUrl=" + shortCode +
                ", longUrl='" + longUrl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Url url = (Url) o;

        if (id != url.id) return false;
        if (!shortCode.equals(url.shortCode)) return false;
        return longUrl.equals(url.longUrl);
    }

}
