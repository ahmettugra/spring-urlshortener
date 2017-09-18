package data.sql.entities.base;

import data.sql.enums.RowStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Enumerated(EnumType.STRING)
    @Column(name = "RowStatus", length = 16, nullable = false)
    private RowStatus rowStatus;
    @Column(name = "Created", nullable = false)
    private Date created;
    @Column(name = "CreatedBy", nullable = false)
    private Integer createdBy;
    @Column(name = "Updated")
    private Date updated;
    @Column(name = "UpdatedBy")
    private Integer updatedBy;
    @Column(name = "Version", nullable = false)
    @Version
    private Long version;

    public String getRowStatus() {
        return rowStatus.toString();
    }

    public void setRowStatus(RowStatus rowStatus) {
        this.rowStatus = rowStatus;
    }

    public Date getCreated() {
        return created;
    }

    private void setCreated(Date created) {
        this.created = created;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdated() {
        return updated;
    }

    private void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Long getVersion() {
        return version;
    }

    private void setVersion(Long version) {
        this.version = version;
    }

    /**
     * Sets createdAt before insert
     */
    @PrePersist
    public void setCreationDate() {
        this.created = new Date();
    }

    /**
     * Sets updatedAt before update
     */
    @PreUpdate
    public void setChangeDate() {
        this.updated = new Date();
    }
}

