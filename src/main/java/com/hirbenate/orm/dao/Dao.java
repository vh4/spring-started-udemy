package com.hirbenate.orm.dao;

import com.hirbenate.orm.entity.InstructorDetail;
import com.hirbenate.orm.entity.Instructor;

public interface Dao {

    void save(Instructor instructor);

    Instructor findInstructionById(Integer id);

    void deleteInstructorById(int theId);

    InstructorDetail findInstructorDetailById(int theId);

    void deleteInstructorDetailById(int theId);


}
