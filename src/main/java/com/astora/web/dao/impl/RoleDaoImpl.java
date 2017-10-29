package com.astora.web.dao.impl;

import com.astora.web.dao.RoleDao;
import com.astora.web.model.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public void create(Role role) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(role);
    }

    @Transactional
    public void update(Role role) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(role);
    }

    @Transactional
    public Role findById(int id) {
        Session session = this.sessionFactory.getCurrentSession();

        return (Role) session.get(Role.class, id);
    }

    @Transactional
    @SuppressWarnings("unchecked")
    public List<Role> findAll() {
        Session session = this.sessionFactory.getCurrentSession();
        return (List<Role>) session.createCriteria(Role.class).list();
    }

    @Transactional
    public void delete(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Role role = (Role) session.load(Role.class, id);
        if (role != null) {
            session.delete(role);
        }
    }
}
