package com.repositories;

import com.model.EntityClass;

import java.util.List;

public interface Dao {

    EntityClass findById(Long id);
    void deleteById(Long id);
    List<EntityClass> findAll();
    EntityClass saveOrUpdate(EntityClass entity);

}
