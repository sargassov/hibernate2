package com.repositories;

import com.model.Consumer;
import com.model.EntityClass;
import com.utils.SessionFactoryUtils;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConsumerDao implements Dao {

    private SessionFactoryUtils sessionFactoryUtils;

    public ConsumerDao(SessionFactoryUtils sessionFactoryUtils) {
        this.sessionFactoryUtils = sessionFactoryUtils;
    }


    @Override
    public EntityClass findById(Long id) {
        try(Session session = sessionFactoryUtils.getSession()){
            session.beginTransaction();
            Consumer c = session.get(Consumer.class, id);
            session.getTransaction().commit();
            return c;
        }
    }

    @Override
    public void deleteById(Long id) {
        try(Session session = sessionFactoryUtils.getSession()){
            session.beginTransaction();
            session.createQuery("delete Consumer c where c.id = id");
            session.getTransaction().commit();
        }
    }

    @Override
    public List<EntityClass> findAll() {
        try(Session session = sessionFactoryUtils.getSession()){
            session.beginTransaction();
            List<EntityClass> entityList = session.createQuery("from Consumer").getResultList();
            session.getTransaction().commit();
            return entityList;
        }
    }

    @Override
    public EntityClass saveOrUpdate(EntityClass entity) {
        try(Session session = sessionFactoryUtils.getSession()){
            session.beginTransaction();
            session.saveOrUpdate(entity);
            session.getTransaction().commit();
            return entity;
        }
    }
}
