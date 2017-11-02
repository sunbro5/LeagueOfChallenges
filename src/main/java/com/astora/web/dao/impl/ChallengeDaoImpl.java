package com.astora.web.dao.impl;

import com.astora.web.dao.ChallengeDao;
import com.astora.web.dao.model.Challenge;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("challengeDao")
public class ChallengeDaoImpl implements ChallengeDao {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void create(Challenge challenge) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(challenge);

    }

    public void update(Challenge challenge) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(challenge);
    }

    public Challenge findById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        return (Challenge) session.get(Challenge.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Challenge> findAll() {
        Session session = this.sessionFactory.getCurrentSession();
        return (List<Challenge>) session.createCriteria(Challenge.class).list();
    }

    public void delete(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Challenge challenge = (Challenge) session.load(Challenge.class, id);
        if (challenge != null) {
            session.delete(challenge);
        }
    }
}
