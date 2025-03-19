package com.mvc.crud.repository;

import com.mvc.crud.dao.EmployedDao;
import com.mvc.crud.entity.Employed;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

//if you want to use createQueryNative() => you can set employed
//if you want to use createQuery() => you must be compare Employed entity class and  FROM  Employed should be the same

public class EmployedRepository implements EmployedDao {
    private final EntityManager db;

    @Autowired
    public EmployedRepository(EntityManager database) {
        this.db = database;
    }

    @Override
    public List<Employed> findAll() {
        TypedQuery<Employed> response = db.createQuery("FROM Employed e", Employed.class);
        return response.getResultList();
    }

    @Override
    @Transactional
    public void save(Employed employed) {
        Query query = db.createNativeQuery(
                "INSERT INTO employed (first_name, last_name, email, phone, picture, city) " +
                        "VALUES (:first_name, :last_name, :email, :phone, :picture, :city)"
        );
        query.setParameter("first_name", employed.getFirstName());
        query.setParameter("last_name", employed.getLastName());
        query.setParameter("email", employed.getEmail());
        query.setParameter("phone", employed.getPhone());
        query.setParameter("picture", employed.getPicture());
        query.setParameter("city", employed.getCity());

        query.executeUpdate();
    }

    @Override
    @Transactional
    public void updated(Employed employed) {
        Query query = db.createNativeQuery(
                "UPDATE employed e SET e.first_name = :first_name, e.last_name = :last_name, " +
                        "e.email = :email, e.phone = :phone, e.picture = :picture, e.city = :city " +
                        "WHERE e.id = :id"
        );
        query.setParameter("first_name", employed.getFirstName());
        query.setParameter("last_name", employed.getLastName());
        query.setParameter("email", employed.getEmail());
        query.setParameter("phone", employed.getPhone());
        query.setParameter("picture", employed.getPicture());
        query.setParameter("city", employed.getCity());
        query.setParameter("id", employed.getId());

        query.executeUpdate();
    }

    @Override
    public Employed findById(Integer id) {
        TypedQuery<Employed> response = db.createQuery("FROM Employed e WHERE e.id = :id", Employed.class);
        response.setParameter("id", id);
        return response.getSingleResult();
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Query query = db.createQuery("DELETE FROM Employed e WHERE e.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
