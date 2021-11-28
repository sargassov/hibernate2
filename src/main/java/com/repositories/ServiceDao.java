package com.repositories;

import com.model.Consumer;
import com.model.EntityClass;
import com.model.Service;
import com.utils.SessionFactoryUtils;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServiceDao implements Dao {
    private SessionFactoryUtils sessionFactoryUtils;

    public ServiceDao(SessionFactoryUtils sessionFactoryUtils) {
        this.sessionFactoryUtils = sessionFactoryUtils;
    }


    @Override
    public EntityClass findById(Long id) {
        try(Session session = sessionFactoryUtils.getSession()){
            session.beginTransaction();
            Service s = session.get(Service.class, id);
            session.getTransaction().commit();
            return s;
        }
    }

    @Override
    public void deleteById(Long id) {
        try(Session session = sessionFactoryUtils.getSession()){
            session.beginTransaction();
            session.createQuery("delete Service s where s.id = id");
            session.getTransaction().commit();
        }
    }

    @Override
    public List<EntityClass> findAll() {
        try(Session session = sessionFactoryUtils.getSession()){
            session.beginTransaction();
            List<EntityClass> entityList = session.createQuery("from Service").getResultList();
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