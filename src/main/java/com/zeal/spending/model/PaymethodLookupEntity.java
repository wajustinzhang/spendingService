package com.zeal.spending.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by wangchun-imac on 1/28/17.
 */
@Entity
@Table(name = "paymethod_lookup", schema = "spending", catalog = "leaz")
public class PaymethodLookupEntity {
    private Integer id;
    private String name;

    @Id
    @Column(name = "ID", nullable = true)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NAME", nullable = true, length = 2147483647)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PaymethodLookupEntity that = (PaymethodLookupEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
