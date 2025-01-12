package com.employe.crud.employed.services;

import com.employe.crud.employed.entity.Employed;

import java.util.List;

public interface EmployedServiceDao {
    void save(Employed employed);
    Employed findBy(Integer id);
    List<Employed> findAll();
    void deleteBy(Integer id);
    void Updated(Employed employed);
}
