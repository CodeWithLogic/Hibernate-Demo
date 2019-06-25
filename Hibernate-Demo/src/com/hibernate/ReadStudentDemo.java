package com.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ReadStudentDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {			
			// create a student object
			System.out.println("Creating new student object...");
			Student tempStudent = new Student("Daffy", "Duck");
			
			// start a transaction
			session.beginTransaction();
			
			// save the student object
			System.out.println("Saving the student...");
			System.out.println(tempStudent);
			session.save(tempStudent);
			
			// commit transaction
			session.getTransaction().commit();
			
			//My New Code for Reading the data from database
			
			//find out the student's primary key
			System.out.println("Saved student. Generated Id: "+tempStudent.getId());
			
			//now get a new Session  and start transaction
			session= factory.getCurrentSession();
			session.beginTransaction();
			//retrieve student based on primary key id
			System.out.println("\nGet: "+tempStudent.getId());
			Student myStudent = session.get(Student.class,tempStudent.getId());
			System.out.println("Get Complete: "+myStudent );
			
			//commit the transaction
			session.getTransaction().commit();
			
			
			
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
	}
}
