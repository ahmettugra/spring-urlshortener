package data.sql.entities.base;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public abstract class BaseAttribute extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Attribute", nullable = false, length = 128)
    private String attribute;

    @Column(name = "Content", length = 2048)
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof BaseAttribute)) {
            return false;
        }
        BaseAttribute other = (BaseAttribute) obj;
        return getId() == other.getId();
    }
}
