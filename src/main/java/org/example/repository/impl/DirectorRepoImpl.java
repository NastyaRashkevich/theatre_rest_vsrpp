package org.example.repository.impl;

import org.example.entity.Director;
import org.example.repository.IRepo;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class DirectorRepoImpl implements IRepo<Director, Long>{

    private Session session;
    private Transaction transaction;

    @Override
    public void add(Director director) throws SQLException {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.save(director);
        transaction.commit();
        session.close();
    }

    @Override
    public List<Director> getAll() throws SQLException {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        List<Director> directors = session.createQuery("FROM Director ").list();
        transaction.commit();
        session.close();
        return directors;
    }

    @Override
    public Director getById(Long id) throws SQLException {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        Director director = session.get(Director.class, id);
        transaction.commit();
        session.close();
        return director;
    }

    @Override
    public Director update(Director director) throws SQLException {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.saveOrUpdate(director);
        transaction.commit();
        session.close();
        return director;
    }

    @Override
    public void delete(Long id) throws SQLException {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        Director director = session.get(Director.class, id);
        session.remove(director);
        transaction.commit();
        session.close();
    }
}
