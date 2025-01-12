package com.employe.crud.employed.dao;

import com.employe.crud.employed.entity.Employed;

import java.util.List;

public interface EmployedDao {
    void save(Employed employed);
    Employed findBy(Integer id);
    List<Employed> findAll();
    void deleteBy(Integer id);
    void Updated(Employed employed);
}
