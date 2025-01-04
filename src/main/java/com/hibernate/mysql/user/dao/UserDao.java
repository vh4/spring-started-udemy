package com.hibernate.mysql.user.dao;

import com.hibernate.mysql.user.entity.UserEntity;

import java.util.List;

public interface UserDao {
    void save(UserEntity user);
    UserEntity findById(Integer id);
    UserEntity findByLastName(String lastName);
    void updateUser(UserEntity user);
    List<UserEntity> findAll();
    void deleteById(Integer id);
    Integer deleteAll();

}
