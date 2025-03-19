package com.mvc.crud.services;

import com.mvc.crud.dao.EmployedDao;
import com.mvc.crud.entity.Employed;
import com.mvc.crud.repository.EmployedRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployedService implements EmployedDao {

    private final EmployedRepository repository;

    public EmployedService(EmployedRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Employed> findAll() {
        return repository.findAll();
    }

    @Override
    public void save(Employed employed) {
        repository.save(employed);
    }

    @Override
    public void updated(Employed employed) {
        repository.updated(employed);
    }

    @Override
    public void delete(Integer id) {
        repository.delete(id);
    }

    @Override
    public Employed findById(Integer id) {
        return repository.findById(id);
    }
}
