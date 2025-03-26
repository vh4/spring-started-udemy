package com.hirbenate.orm.dao;

import com.hirbenate.orm.entity.Course;
import com.hirbenate.orm.entity.InstructorDetail;
import com.hirbenate.orm.entity.Instructor;

import java.util.List;

public interface Dao {

    void save(Instructor instructor);

    Instructor findInstructionById(Integer id);

    void deleteInstructorById(int theId);

    InstructorDetail findInstructorDetailById(int theId);

    void deleteInstructorDetailById(int theId);

    List<Course> findCoursesByInstructorId(int theId);

    //you can use join for get relation.
    Instructor findInstructorByIdJoinFetch(int theId);

    Course findCourseById(int theId);

    void update(Instructor tempInstructor);

    void update(Course tempCourse);

    void deleteCourseById(int theId);

    void save(Course course);

    Course findCourseAndReviewsByCourseId(int theId);


}
