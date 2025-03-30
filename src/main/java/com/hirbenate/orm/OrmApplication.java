package com.hirbenate.orm;

import com.hirbenate.orm.dao.Dao;
import com.hirbenate.orm.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.config.ScheduledTasksBeanDefinitionParser;

import java.util.List;

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

//			createInstructorWithCourses(dao);

//			findInstructorWithCourses(dao);

			// reverse dari findInstructorWithCourses
//			findCoursesForInstructor(dao);


			//search relation with join query builder not orm
//			findInstructorWithCoursesJoinFetch(dao);

//			updateInstructor(dao);

//			updateCourse(dao);

//			deleteCourse(dao);

//			createCourseAndReviews(dao);

//			retrieveCourseAndReviews(dao);

			createCourseAndStudents(dao);

//			findCourseAndStudents(dao);

		};
	}
	private void findCourseAndStudents(Dao dao) {

		int theId = 10;
		Course tempCourse = dao.findCourseAndStudentsByCourseId(theId);

		System.out.println("Loaded course: " + tempCourse);
		System.out.println("Students: " + tempCourse.getStudents());

		System.out.println("Done!");
	}

//	private void createCourseAndStudents(Dao dao) {
//		int theId = 13;
//		Course course = dao.findCourseById(theId);
//
//		if (course == null) {
//			System.out.println("Course with ID " + theId + " not found.");
//			return;
//		}
//
//		Student tempStudent1 = new Student("john@luv2code.com", "Doe", "john");
//		course.addStudent(tempStudent1);
//
//		System.out.println("Associated students: " + course.getStudents());
//
//		dao.update(course);
//		System.out.println("Done!");
//	}

	private void createCourseAndStudents(Dao dao) {
		int courseId = 13;
		int studentId = 3;

		Course course = dao.findCourseById(courseId);
		Student student = dao.findStudentById(studentId);

		if (course == null || student == null) {
			System.out.println("Course or Student not found.");
			return;
		}

		// Ensure Student#3 is not already in Course#13
		if (!course.getStudents().contains(student)) {
			course.addStudent(student);
			dao.update(course); // Merge course instead of student
			System.out.println("Student added successfully.");
		} else {
			System.out.println("Student is already enrolled in this course.");
		}
	}



	private void deleteCourseAndReviews(Dao dao) {

		int theId = 10;

		System.out.println("Deleting course id: " + theId);

		dao.deleteCourseById(theId);

		System.out.println("Done!");
	}

	private void retrieveCourseAndReviews(Dao dao) {

		// get the course and reviews
		int theId = 3;
		Course tempCourse = dao.findCourseAndReviewsByCourseId(theId);

		// print the course
		System.out.println(tempCourse);

		// print the reviews
		System.out.println(tempCourse.getReviews());
	}

	private void createCourseAndReviews(Dao dao) {

		// create a course
		Course tempCourse = new Course("Pacman - How To Score One Million Points");

		// add some reviews
		tempCourse.addReview(new Review("Great course ... loved it!"));
		tempCourse.addReview(new Review("Cool course, job well done."));
		tempCourse.addReview(new Review("What a dumb course, you are an idiot!"));

		// save the course ... and leverage the cascade all
		System.out.println("Saving the course");
		System.out.println(tempCourse);
		System.out.println(tempCourse.getReviews());

		dao.save(tempCourse);

		System.out.println("Done!");
	}


	private void deleteCourse(Dao dao) {

		int theId = 1;

		System.out.println("Deleting course id: " + theId);

		dao.deleteCourseById(theId);

		System.out.println("Done!");
	}

	private void updateCourse(Dao dao) {

		int theId = 1;

		// find the course
		System.out.println("Finding course id: " + theId);
		Course tempCourse = dao.findCourseById(theId);

		// update the course
		System.out.println("Updating course id: " + theId);
		tempCourse.setTitle("Enjoy the Simple Things");

		dao.update(tempCourse);

		System.out.println("Done!");
	}

	private void updateInstructor(Dao dao) {

		int theId = 1;

		// find the instructor
		System.out.println("Finding instructor id: " + theId);
		Instructor tempInstructor = dao.findInstructionById(theId);

		// update the instructor
		System.out.println("Updating instructor id: " + theId);
		tempInstructor.setLastName("TESTER");

		dao.update(tempInstructor);

		System.out.println("Done!");
	}

	private void findInstructorWithCoursesJoinFetch(Dao dao) {

		int theId = 2;

		// find the instructor
		System.out.println("Finding instructor id: " + theId);
		Instructor tempInstructor = dao.findInstructorByIdJoinFetch(theId);

		if (tempInstructor == null) {
			System.out.println("No instructor found with ID: " + theId);
			return; // Stop execution to avoid NullPointerException
		}

		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("The associated courses: " + tempInstructor.getCourses());

		System.out.println("Done!");
	}



	private void findCoursesForInstructor(Dao dao) {

		int theId = 2;
		// find instructor
		System.out.println("Finding instructor id: " + theId);

		Instructor tempInstructor = dao.findInstructionById(theId);

		System.out.println("tempInstructor: " + tempInstructor);

		// find courses for instructor
		System.out.println("Finding courses for instructor id: " + theId);
		List<Course> courses = dao.findCoursesByInstructorId(theId);

		// associate the objects
		tempInstructor.setCourses(courses);

		System.out.println("the associated courses: " + tempInstructor.getCourses());

		System.out.println("Done!");
	}

	private void findInstructorWithCourses(Dao dao) {

		int theId = 2;
		System.out.println("Finding instructor id: " + theId);

		Instructor tempInstructor = dao.findInstructionById(theId);

		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("the associated courses: " + tempInstructor.getCourses());

		System.out.println("Done!");
	}


	private void createInstructorWithCourses(Dao dao) {

		Instructor instructorTemp = new Instructor(
				"Tony", "Tony", "Tony.Tony@luv2code.com"
		);

		InstructorDetail tempInstructorDetail =
				new InstructorDetail(
						"http://www.youtube.com",
						"Tony Tony");

		instructorTemp.setInstructorDetail(tempInstructorDetail);

		// associate the objects
		instructorTemp.setInstructorDetail(tempInstructorDetail);

		// create some courses
		Course tempCourse1 = new Course("Rrr Tony");
		Course tempCourse2 = new Course("Classic");

		// add courses to instructor
		instructorTemp.add(tempCourse1);
		instructorTemp.add(tempCourse2);

		// save the instructor
		//
		// NOTE: this will ALSO save the courses
		// because of CascadeType.PERSIST
		//
		System.out.println("Saving instructor: " + instructorTemp);
		System.out.println("The courses: " + instructorTemp.getCourses());
		dao.save(instructorTemp);

		System.out.println("Done!");

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
