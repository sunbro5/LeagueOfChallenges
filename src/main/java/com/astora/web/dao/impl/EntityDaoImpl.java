package com.astora.web.dao.impl;

import com.astora.web.dao.EntityDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class EntityDaoImpl<T> implements EntityDao<T> {

    protected SessionFactory sessionFactory;
    private Class<T> type;

    protected EntityDaoImpl(Class<T> type) {
        this.type = type;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public void create(T role) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(role);
    }

    @Transactional
    public void update(T role) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(role);
    }

    @Transactional
    public T findById(int id) {
        Session session = this.sessionFactory.getCurrentSession();

        return (T) session.get(type, id);
    }

    @Transactional
    public List<T> findAll() {
        Session session = this.sessionFactory.getCurrentSession();
        return (List<T>) session.createCriteria(type).list();
    }

    @Transactional
    public void delete(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        T role = (T) session.load(type, id);
        if (role != null) {
            session.delete(role);
        }
    }
}
