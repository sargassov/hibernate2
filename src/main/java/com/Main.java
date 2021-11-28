package com;

import com.model.Consumer;
import com.model.EntityClass;
import com.model.Product;
import com.model.Service;
import com.repositories.ConsumerDao;
import com.repositories.Dao;
import com.repositories.ProductDao;
import com.repositories.ServiceDao;
import com.utils.SessionFactoryUtils;
import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.GregorianCalendar;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("com");

        SessionFactoryUtils utils = new SessionFactoryUtils();
        utils.init();

        Dao productsRep = context.getBean(ProductDao.class);
        Dao consumersRep = context.getBean(ConsumerDao.class);
        Dao servicesRep = context.getBean(ServiceDao.class);

        try(Session session = utils.getSession()){
            System.out.println("\n\n\n==============1=================");
            session.beginTransaction();
            Service service = (Service) servicesRep.findById(1L);// достали и посмотрели один из сервисов покупки
            System.out.println(service.toString());

            Consumer consumer = new Consumer("Brian"); // сформировали нового покупателя
            consumersRep.saveOrUpdate(consumer);
            System.out.println(consumersRep.findAll());

            session.getTransaction().commit();
            System.out.println("==============1=================\n\n\n");
        }

        try(Session session = utils.getSession()){
            System.out.println("==============2=================");
            session.beginTransaction();
            Product caviar = (Product) productsRep.findById(2L); // достаем продукт из базы
            Service buyService = new Service( //новый пользователь покупает его в магазине
                    (Consumer) consumersRep.findById(4L),
                    caviar,
                    new GregorianCalendar(),   // дата и текущая стоимость сохранены
                    caviar.getCost()
            );
            servicesRep.saveOrUpdate(buyService);

            session.getTransaction().commit();
        }

        try(Session session = utils.getSession()) {
            session.beginTransaction();
            List<EntityClass> allServices = servicesRep.findAll();
            System.out.println(allServices);
            session.getTransaction().commit();
            System.out.println("==============2=================\n\n\n");
        }



        utils.shutdown();
    }
}
