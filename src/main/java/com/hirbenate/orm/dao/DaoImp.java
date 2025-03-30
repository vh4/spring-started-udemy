package com.hirbenate.orm.dao;

import com.hirbenate.orm.entity.Course;
import com.hirbenate.orm.entity.Instructor;
import com.hirbenate.orm.entity.InstructorDetail;
import com.hirbenate.orm.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DaoImp  implements Dao{

    private final EntityManager db;

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
        return query.getResultList();
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int theId) {
        try {
            TypedQuery<Instructor> query = db.createQuery(
                    "select i from Instructor i "
                            + "JOIN FETCH i.courses "
                            + "JOIN FETCH i.instructorDetail "
                            + "where i.id = :data", Instructor.class);
            query.setParameter("data", theId);

            // Execute query
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Course findCourseById(int theId) {
        return db.find(Course.class, theId);
    }


    @Override
    @Transactional
    public void update(Instructor tempInstructor) {
        db.merge(tempInstructor);
    }

    @Override
    @Transactional
    public void update(Course tempCourse) {
        db.merge(tempCourse);
    }

    @Override
    @Transactional
    public void deleteCourseById(int id){
        Course course = db.find(Course.class, id);
        db.remove(course);

    }

    @Override
    @Transactional
    public void save(Course theCourse) {
        db.persist(theCourse);
    }

    @Override
    public Course findCourseAndReviewsByCourseId(int id){
        // create query
        TypedQuery<Course> query = db.createQuery(
                "select c from Course c "
                        + "JOIN FETCH c.reviews "
                        + "where c.id = :data", Course.class);

        query.setParameter("data", id);

        // execute query
        return query.getSingleResult();
    }

    public Course findCourseAndStudentsByCourseId(int id){
        TypedQuery<Course> query = db.createQuery(
                "select c from Course c join fetch c.students where c.id = :id", Course.class
        );

        query.setParameter("id", id);
        return query.getSingleResult();

    }

    @Override
    public Student findStudentById(int id){
        return db.find(Student.class, id);
    }

}
