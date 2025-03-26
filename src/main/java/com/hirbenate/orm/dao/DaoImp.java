package com.hirbenate.orm.dao;

import com.hirbenate.orm.entity.Course;
import com.hirbenate.orm.entity.Instructor;
import com.hirbenate.orm.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DaoImp  implements Dao{

    private EntityManager db;

    @Autowired
    public DaoImp(EntityManager entityManager){
        this.db = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor instructor){
        db.persist(instructor);
    }

    @Override
    public Instructor findInstructionById(Integer id){
        return db.find(Instructor.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id){
        Instructor instructor = db.find(Instructor.class, id);

        List<Course> courseList = instructor.getCourses();

        for(Course x : courseList){
            x.setInstructor(null);
        }

        db.remove(instructor);

    }

    @Override
    public InstructorDetail findInstructorDetailById(int id){
        return db.find(InstructorDetail.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int id){
        InstructorDetail detail = db.find(InstructorDetail.class, id);
        db.remove(detail);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int id) {
        // create query
        TypedQuery<Course> query = db.createQuery(
                "from Course where instructor.id = :data", Course.class);
        query.setParameter("data", id);

        // execute query
        List<Course> courses = query.getResultList();
        return courses;
    }

}
