package com.hirbenate.orm;

import com.hirbenate.orm.dao.Dao;
import com.hirbenate.orm.entity.Instructor;
import com.hirbenate.orm.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OrmApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrmApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(Dao dao){
		return runner -> {

			//createInstructor
//			createInstructor(dao);

			//deleteInstructor
//			deleteInstructor(dao);

			//findInstructorDetail
//			findInstructorDetail(dao);

//			deleteInstructorDetail(dao);

		};
	}

	private void deleteInstructorDetail(Dao dao) {

		int theId = 3;
		System.out.println("Deleting instructor detail id: " + theId);

		dao.deleteInstructorDetailById(theId);

		System.out.println("Done!");
	}

	private void findInstructorDetail(Dao dao) {

		// get the instructor detail object
		int theId = 2;
		InstructorDetail tempInstructorDetail = dao.findInstructorDetailById(theId);

		// print the instructor detail
		System.out.println("tempInstructorDetail: " + tempInstructorDetail);

		// print the associated instructor
		System.out.println("the associated instructor: " + tempInstructorDetail.getInstructor());

		System.out.println("Done!");
	}

	private void deleteInstructor(Dao dao) {

		int theId = 4;
		System.out.println("Deleting instructor id: " + theId);

		dao.deleteInstructorById(theId);

		System.out.println("Done!");
	}


	private void findInstructor(Dao dao) {
		int theId = 3;

		System.out.println("Finding instructor id: " + theId);

		Instructor tempInstructor = dao.findInstructionById(theId);

		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("the associated instructorDetail only: " + tempInstructor.getInstructorDetail());

	}

	private void createInstructor(Dao dao) {
		Instructor tempInstructor = new Instructor(
				"Anak", "Mami", "anakmami@luv2code.com"
		);

		InstructorDetail tempInstructorDetail =
				new InstructorDetail(
						"http://www.mami.com/youtube",
						"Mami");

		tempInstructor.setInstructorDetail(tempInstructorDetail);

		System.out.println("Saving instructor: " + tempInstructor);
		dao.save(tempInstructor);
		System.out.println("Done!");

	};

}
