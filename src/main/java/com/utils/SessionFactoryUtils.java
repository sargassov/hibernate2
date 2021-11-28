package com.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class SessionFactoryUtils {

    private SessionFactory factory;

    @PostConstruct
    public void init(){
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
    }

    public Session getSession() {return factory.getCurrentSession();}

    public void shutdown(){
        if(factory != null){
            factory.close();
        }
    }
}
