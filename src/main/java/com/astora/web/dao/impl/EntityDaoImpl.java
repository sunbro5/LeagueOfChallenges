package com.astora.web.dao.impl;

import com.astora.web.dao.EntityDao;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Transactional
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

    public void create(T object) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(object);
    }

    public void update(T object) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(object);
    }

    public T findById(int id) {
        Session session = this.sessionFactory.getCurrentSession();

        return (T) session.get(type, id);
    }

    public List<T> findAll() {
        Session session = this.sessionFactory.getCurrentSession();
        return (List<T>) session.createCriteria(type).list();
    }

    public void delete(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        T object = (T) session.load(type, id);
        if (object != null) {
            session.delete(object);
        }
    }

    public T getByUniqueColumnValue(String columnName, Object value){
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(type);
        criteria.add(Restrictions.eq(columnName, value));
        return (T) criteria.uniqueResult();
    }

    public List<T> getListLikeColumnValue(String columnName, String value){
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(type);
        criteria.add(Restrictions.like(columnName, value, MatchMode.ANYWHERE));
        return criteria.list();
    }

    public List<T> getListInColumnValue(String columnName,Collection collection){
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(type);
        criteria.add(Restrictions.in(columnName, collection));
        return criteria.list();
    }

}
