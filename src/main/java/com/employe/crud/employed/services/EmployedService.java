package com.employe.crud.employed.services;

import com.employe.crud.employed.dao.EmployedDao;
import com.employe.crud.employed.entity.Employed;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployedService implements EmployedServiceDao{
    private final EmployedDao employed;

    public EmployedService(EmployedDao employedDao){
        this.employed = employedDao;
    }

    @Override
    public void Updated(Employed employed) {
        this.employed.Updated(employed);
    }

    @Override
    public void save(Employed employed) {
        this.employed.save(employed);
    }

    @Override
    public Employed findBy(Integer id) {
        return this.employed.findBy(id);
    }

    @Override
    public List<Employed> findAll() {
        return this.employed.findAll();
    }

    @Override
    public void deleteBy(Integer id) {
        this.employed.deleteBy(id);
    }
}


/*pakai JPA Repository */
/*
@Service
public class EmployedService implements EmployedServiceDao{
    private final EmployedDao employed;

    public EmployedService(EmployedDao employedDao){
        this.employed = employedDao;
    }

    @Override
    public void Updated(Employed employed) {
        this.employed.save(employed);
    }

    @Override
    public void save(Employed employed) {
        this.employed.save(employed);
    }

    @Override
    public Optional<Employed> findBy(Integer id) {
        return this.employed.findById(id);
    }

    @Override
    public List<Employed> findAll() {
        return this.employed.findAll();
    }

    @Override
    public void deleteBy(Integer id) {
        this.employed.deleteById(id);
    }
}
 */
