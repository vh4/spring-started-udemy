package com.hirbenate.orm.dao;

import com.hirbenate.orm.entity.Course;
import com.hirbenate.orm.entity.Instructor;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DaoImp  implements Dao{

    private EntityManager entityManager;

    @Autowired
    public DaoImp(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor instructor){
        entityManager.persist(instructor);
    }

    @Override
    public Instructor findInstructionById(Integer id){
        return entityManager.find(Instructor.class, id);
    }

    public void deleteInstructorById(Integer id){
        Instructor instructor = entityManager.find(Instructor.class, id);

        List<Course> courseList = instructor.getCourses();

        for(Course x : courseList){
            x.setInstructor(null);
        }

        entityManager.remove(instructor);

    }



}
