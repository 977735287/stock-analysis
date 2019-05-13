package per.san.generate.domain;

import javax.persistence.Column;
import java.util.Date;

/**
 * description: 基本字段
 *
 * @author sanchar
 * @date 12/28/2018 08:52
 * lastUpdateBy: sanchar
 * lastUpdateDate: 12/28/2018
 */
public class BaseDomain {

    /**
     * 创建人
     */
    @Column(name = "created_by", columnDefinition = "BIGINT")
    private Long createdBy;

    /**
     * 创建时间
     */
    @Column(name = "creation_date", columnDefinition = "DATETIME")
    private Date creationDate;

    /**
     * 更新人
     */
    @Column(name = "last_updated_by", columnDefinition = "BIGINT")
    private Long lastUpdatedBy;

    /**
     * 更新时间
     */
    @Column(name = "last_update_date", columnDefinition = "DATETIME")
    private Date lastUpdateDate;

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Long getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(Long lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
}
