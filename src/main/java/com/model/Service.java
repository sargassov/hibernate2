package com.model;

import javax.persistence.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Entity
@Table(name = "services")
public class Service extends EntityClass  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "consumers_id", referencedColumnName = "id")
    private Consumer consumer;

    @ManyToOne
    @JoinColumn(name = "products_id", referencedColumnName = "id")
    private Product product;

    @Column(name = "time")
    private String time;

    @Column(name = "cost")
    private int cost;

    public Service() {
    }

    public Service(Consumer consumer, Product product, GregorianCalendar time, int cost) {
        this.consumer = consumer;
        this.product = product;
        this.time = time.get(Calendar.DAY_OF_MONTH) + "."
                + (time.get(Calendar.MONTH) + 1)
                + "." + time.get(Calendar.YEAR);
        this.cost = cost;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Consumer getConsumer() {
        return consumer;
    }

    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return consumer.getName() + " was bought " + product.getTitle() + " at " + time + " for " + cost;
    }
}
