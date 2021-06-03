package org.example.repository.impl;

import org.example.entity.Performance;
import org.example.entity.Repetition;
import org.example.repository.IRepo;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class RepetitionRepoImpl implements IRepo<Repetition, Long> {

    private Session session;
    private Transaction transaction;

    @Override
    public void add(Repetition repetition) throws SQLException {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.save(repetition);
        transaction.commit();
        session.close();
    }

    @Override
    public List<Repetition> getAll() throws SQLException {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        List<Repetition> repetitions = session.createQuery("FROM Repetition ").list();
        transaction.commit();
        session.close();
        return repetitions;
    }

    @Override
    public Repetition getById(Long id) throws SQLException {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        Repetition repetition = session.get(Repetition.class, id);
        transaction.commit();
        session.close();
        return repetition;
    }

    @Override
    public Repetition update(Repetition repetition) throws SQLException {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.saveOrUpdate(repetition);
        transaction.commit();
        session.close();
        return repetition;
    }

    @Override
    public void delete(Long id) throws SQLException {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        Repetition repetition = session.get(Repetition.class, id);
        session.remove(repetition);
        transaction.commit();
        session.close();
    }
}
