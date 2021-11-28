package com.repositories;

import com.model.EntityClass;
import com.model.Product;
import com.model.Service;
import com.utils.SessionFactoryUtils;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDao implements Dao {
    private SessionFactoryUtils sessionFactoryUtils;

    public ProductDao(SessionFactoryUtils sessionFactoryUtils) {
        this.sessionFactoryUtils = sessionFactoryUtils;
    }


    @Override
    public EntityClass findById(Long id) {
        try(Session session = sessionFactoryUtils.getSession()){
            session.beginTransaction();
            Product p = session.get(Product.class, id);
            session.getTransaction().commit();
            return p;
        }
    }

    @Override
    public void deleteById(Long id) {
        try(Session session = sessionFactoryUtils.getSession()){
            session.beginTransaction();
            session.createQuery("delete Product p where p.id = id");
            session.getTransaction().commit();
        }
    }

    @Override
    public List<EntityClass> findAll() {
        try(Session session = sessionFactoryUtils.getSession()){
            session.beginTransaction();
            List<EntityClass> entityList = session.createQuery("from Product").getResultList();
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
