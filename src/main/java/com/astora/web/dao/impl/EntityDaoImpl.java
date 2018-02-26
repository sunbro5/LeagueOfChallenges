package com.astora.web.dao.impl;

import com.astora.web.dao.EntityDao;
import com.astora.web.utils.CustomValidationUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.Collection;
import java.util.List;

public class EntityDaoImpl<T> implements EntityDao<T> {

    @Autowired
    protected SessionFactory sessionFactory;

    private Class<T> type;

    protected EntityDaoImpl(Class<T> type) {
        this.type = type;
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

    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        Session session = this.sessionFactory.getCurrentSession();
        return session.createQuery("FROM " + type.getName()).getResultList();
    }

    public void delete(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        T object = (T) session.load(type, id);
        if (object != null) {
            session.delete(object);
        }
    }

    @SuppressWarnings("unchecked")
    public T getByUniqueColumnValue(String columnName, Object value) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery(String.format("FROM %s WHERE %s = :value", type.getName(), columnName));
        query.setParameter("value", value);
        List<T> list = query.getResultList();
        if(CustomValidationUtils.isEmpty(list)){
            return null;
        }
        return list.get(0);
    }

    @SuppressWarnings("unchecked")
    public List<T> getListLikeColumnValue(String columnName, String value) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery(String.format("FROM %s WHERE %s LIKE :value", type.getName(), columnName));
        query.setParameter("value", "%" + value + "%");
        return query.getResultList();
    }

}
