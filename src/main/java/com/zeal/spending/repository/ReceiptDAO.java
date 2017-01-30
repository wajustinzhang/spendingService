package com.zeal.spending.repository;

import com.zeal.spending.model.ReceiptEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by wangchun-imac on 1/28/17.
 */
@Repository
public interface ReceiptDAO {
    public void save(ReceiptEntity receiptEntity);
    public int update(ReceiptEntity receiptEntity);
    public int delete(String id);
    public List<ReceiptEntity> getAll();
    public ReceiptEntity get(String id);
}
