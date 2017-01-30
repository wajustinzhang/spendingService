package com.zeal.spending.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * Created by wangchun-imac on 1/28/17.
 */
@Entity
@Table(name = "receipt", schema = "spending", catalog = "leaz")
public class ReceiptEntity {
    private String id;
    private String ownerUuid;
    private Integer categoryId;
    private String sLocation;
    private Date sTime;
    private String sReason;
    private BigDecimal sAmount;
    private BigDecimal sTip;
    private Integer pMethod;

    @Id
    @Column(name = "ID", nullable = true)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "OWNER_UUID", nullable = true)
    public String getOwnerUuid() {
        return ownerUuid;
    }

    public void setOwnerUuid(String ownerUuid) {
        this.ownerUuid = ownerUuid;
    }

    @Basic
    @Column(name = "CATEGORY_ID", nullable = true)
    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    @Basic
    @Column(name = "S_LOCATION", nullable = true, length = 2147483647)
    public String getsLocation() {
        return sLocation;
    }

    public void setsLocation(String sLocation) {
        this.sLocation = sLocation;
    }

    @Basic
    @Column(name = "S_TIME", nullable = true)
    public Date getsTime() {
        return sTime;
    }

    public void setsTime(Date sTime) {
        this.sTime = sTime;
    }

    @Basic
    @Column(name = "S_REASON", nullable = true, length = 2147483647)
    public String getsReason() {
        return sReason;
    }

    public void setsReason(String sReason) {
        this.sReason = sReason;
    }

    @Basic
    @Column(name = "S_AMOUNT", nullable = true, precision = 2)
    public BigDecimal getsAmount() {
        return sAmount;
    }

    public void setsAmount(BigDecimal sAmount) {
        this.sAmount = sAmount;
    }

    @Basic
    @Column(name = "S_TIP", nullable = true, precision = 2)
    public BigDecimal getsTip() {
        return sTip;
    }

    public void setsTip(BigDecimal sTip) {
        this.sTip = sTip;
    }

    @Basic
    @Column(name = "P_METHOD", nullable = true)
    public Integer getpMethod() {
        return pMethod;
    }

    public void setpMethod(Integer pMethod) {
        this.pMethod = pMethod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReceiptEntity that = (ReceiptEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (ownerUuid != null ? !ownerUuid.equals(that.ownerUuid) : that.ownerUuid != null) return false;
        if (categoryId != null ? !categoryId.equals(that.categoryId) : that.categoryId != null) return false;
        if (sLocation != null ? !sLocation.equals(that.sLocation) : that.sLocation != null) return false;
        if (sTime != null ? !sTime.equals(that.sTime) : that.sTime != null) return false;
        if (sReason != null ? !sReason.equals(that.sReason) : that.sReason != null) return false;
        if (sAmount != null ? !sAmount.equals(that.sAmount) : that.sAmount != null) return false;
        if (sTip != null ? !sTip.equals(that.sTip) : that.sTip != null) return false;
        if (pMethod != null ? !pMethod.equals(that.pMethod) : that.pMethod != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (ownerUuid != null ? ownerUuid.hashCode() : 0);
        result = 31 * result + (categoryId != null ? categoryId.hashCode() : 0);
        result = 31 * result + (sLocation != null ? sLocation.hashCode() : 0);
        result = 31 * result + (sTime != null ? sTime.hashCode() : 0);
        result = 31 * result + (sReason != null ? sReason.hashCode() : 0);
        result = 31 * result + (sAmount != null ? sAmount.hashCode() : 0);
        result = 31 * result + (sTip != null ? sTip.hashCode() : 0);
        result = 31 * result + (pMethod != null ? pMethod.hashCode() : 0);
        return result;
    }
}
