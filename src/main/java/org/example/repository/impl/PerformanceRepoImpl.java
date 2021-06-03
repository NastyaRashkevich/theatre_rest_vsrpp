package org.example.repository.impl;

import org.example.entity.Performance;
import org.example.repository.IRepo;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class PerformanceRepoImpl implements IRepo<Performance, Long> {

    private Session session;
    private Transaction transaction;

    @Override
    public void add(Performance performance) throws SQLException {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.save(performance);
        transaction.commit();
        session.close();
    }

    @Override
    public List<Performance> getAll() throws SQLException {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        List<Performance> performances = session.createQuery("FROM Performance ").list();
        transaction.commit();
        session.close();
        return performances;
    }

    @Override
    public Performance getById(Long id) throws SQLException {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        Performance performance = session.get(Performance.class, id);
        transaction.commit();
        session.close();
        return performance;
    }

    @Override
    public Performance update(Performance performance) throws SQLException {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.saveOrUpdate(performance);
        transaction.commit();
        session.close();
        return performance;
    }

    @Override
    public void delete(Long id) throws SQLException {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        Performance performance = session.get(Performance.class, id);
        session.remove(performance);
        transaction.commit();
        session.close();
    }
}
