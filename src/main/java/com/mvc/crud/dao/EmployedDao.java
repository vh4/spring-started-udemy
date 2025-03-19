package com.mvc.crud.dao;

import com.mvc.crud.entity.Employed;
import java.util.List;

public interface EmployedDao {
    void save(Employed employed);
    List<Employed> findAll();
    Employed findById(Integer id);
    void updated(Employed employed);
    void delete(Integer id);
}
