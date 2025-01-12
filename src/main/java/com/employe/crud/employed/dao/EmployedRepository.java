package com.employe.crud.employed.dao;

import com.employe.crud.employed.entity.Employed;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployedRepository implements EmployedDao{
    private final EntityManager db;

    @Autowired
    public EmployedRepository(EntityManager entityManager){
        this.db = entityManager;
    }

    @Override
    @Transactional
    public void deleteBy(Integer id) {
        Employed employed = db.find(Employed.class, id);
        db.remove(employed);
    }

    @Override
    public List<Employed> findAll() {
        TypedQuery<Employed> employedTypedQuery = db.createQuery("FROM Employed", Employed.class);
        return employedTypedQuery.getResultList();
    }

    @Override
    public Employed findBy(Integer id) {
        return db.find(Employed.class, id);
    }

    @Override
    @Transactional
    public void save(Employed employed) {
         db.persist(employed);
    }

    @Override
    @Transactional
    public void Updated(Employed employed) {
        db.merge(employed);
    }
}
