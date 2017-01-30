package com.zeal.spending.repository.impl;

import com.zeal.spending.model.ReceiptEntity;
import com.zeal.spending.repository.ReceiptDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by wangchun-imac on 1/28/17.
 */
public class ReceiptDaoImpl implements ReceiptDAO{
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(ReceiptEntity receiptEntity) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(receiptEntity);
        tx.commit();
        session.close();
    }

    @Override
    public int update(ReceiptEntity receiptEntity) {
        ReceiptEntity receiptEntity1 = get(receiptEntity.getId());
        if(receiptEntity1 != null) {
            Session session = this.sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            session.persist(receiptEntity);
            tx.commit();
            session.close();

            return 1;
        }

        return 0;
    }

    @Override
    public int delete(String id) {
        return 0;
    }

    @Override
    public List<ReceiptEntity> getAll() {
        Session session = this.sessionFactory.openSession();
        List<ReceiptEntity> receiptEntities = session.createQuery("from ReceiptEntity").list();
        session.close();
        return receiptEntities;
    }

    @Override
    public ReceiptEntity get(String id) {
        Session session = this.sessionFactory.openSession();
        ReceiptEntity receiptEntity = (ReceiptEntity)session.createQuery("from ReceiptEntity where ReceiptEntity.id = :id").list().get(0);

        return receiptEntity;
    }
}
