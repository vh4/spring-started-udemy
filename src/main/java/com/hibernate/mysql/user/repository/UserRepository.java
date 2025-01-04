package com.hibernate.mysql.user.repository;

import com.hibernate.mysql.user.dao.UserDao;
import com.hibernate.mysql.user.entity.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository implements UserDao {

    private final EntityManager entityManager;

    @Autowired
    public UserRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(UserEntity user){
        entityManager.persist(user);
    }

    @Override
    public UserEntity findById(Integer id) {
        return entityManager.find(UserEntity.class, id);
    }

    @Override
    public List<UserEntity> findAll() {
        // create query
        TypedQuery<UserEntity> theQuery = entityManager.createQuery("FROM UserEntity", UserEntity.class);

        // return query results
        return theQuery.getResultList();
    }

    @Override
    public UserEntity findByLastName(String lastName) {
        TypedQuery<UserEntity> user = entityManager.createQuery("FROM UserEntity where lastName = :lastName", UserEntity.class);
        user.setParameter("lastName", lastName);
        user.setMaxResults(1);
        return user.getSingleResult();
    }

    @Override
    @Transactional
    public void updateUser(UserEntity user) {
        entityManager.merge(user);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        UserEntity user = entityManager.find(UserEntity.class, id);
        entityManager.remove(user);
    }

    @Override
    public Integer deleteAll() {
        return entityManager.createQuery("DELETE FROM UserEntity").executeUpdate();
    }
}
