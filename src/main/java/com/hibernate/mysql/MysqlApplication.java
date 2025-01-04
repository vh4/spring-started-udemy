package com.hibernate.mysql;

import com.hibernate.mysql.user.dao.UserDao;
import com.hibernate.mysql.user.entity.UserEntity;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class MysqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(MysqlApplication.class, args);
	}

	@Bean
	public CommandLineRunner CommandLineRunner(UserDao user){
		return runner -> {
			CrudUser(user);
		};
	}

	public void CrudUser(UserDao user){

		//create
		System.out.println("Creating new student object ...");
		UserEntity tempStudent = new UserEntity("Bang", "Lari", "fuck@luv2code.com");

		// save the student
		System.out.println("Saving the student ...");
		user.save(tempStudent);

		// display id of the saved student
		int theId = tempStudent.getId();
		System.out.println("Saved student. Generated id: " + theId);

		// retrieve student based on the id: primary key
		System.out.println("Retrieving student with id: " + theId);
		UserEntity myStudent = user.findById(theId);

		// display student
		System.out.println("Found the student: " + myStudent);

		//find All
		List<UserEntity> users = user.findAll();
		for (UserEntity x : users) {
			System.out.println(x);
		}

		//Query find by lastname
		UserEntity findByLastName = user.findByLastName(tempStudent.getLastName());
		System.out.println("Found the student by lastName: " + findByLastName);

		//updated by entity
		UserEntity data = user.findById(tempStudent.getId());
		data.setFistName("anak update");
		user.updateUser(data);
		UserEntity updated = user.findById(tempStudent.getId());
		System.out.println("Updated -> : " + updated);

		//delete
		int studentId = updated.getId();
		System.out.println("Deleting student id: " + studentId);
		user.deleteById(studentId);


		//delete all
//		System.out.println("Deleting all students");
//		int numRowsDeleted = user.deleteAll();
//		System.out.println("Deleted row count: " + numRowsDeleted);

	}

}
