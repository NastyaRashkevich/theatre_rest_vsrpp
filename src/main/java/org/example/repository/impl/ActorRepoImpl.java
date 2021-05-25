package org.example.repository.impl;

import org.example.entity.Actor;
import org.example.repository.IRepo;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class ActorRepoImpl implements IRepo<Actor, Long> {

    private Session session;
    private Transaction transaction;

    @Override
    public void add(Actor actor) throws SQLException {
        session = HibernateUtil.getSessionFactory().openSession();  //подкл хибер, открываем сессию
        transaction = session.beginTransaction();  //начинаем транзакцию
        session.save(actor);  //сохр актёра
        transaction.commit();  //метод коммит - фиксация, завершает все изменения бд
        session.close();  //сессия закрылась
    }

    @Override
    public List<Actor> getAll() throws SQLException {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        List<Actor> actors = session.createQuery("FROM Actor ").list();
        transaction.commit();
        session.close();
        return actors;
    }

    @Override
    public Actor getById(Long id) throws SQLException {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        Actor actor = session.get(Actor.class, id);
        transaction.commit();
        session.close();
        return actor;
    }

    @Override
    public Actor update(Actor actor) throws SQLException {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.saveOrUpdate(actor);
        transaction.commit();
        session.close();
        return actor;
    }

    @Override
    public void delete(Long id) throws SQLException {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        Actor actor = session.get(Actor.class, id);
        session.remove(actor);
        transaction.commit();
        session.close();
    }
}
