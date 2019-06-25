package com.hibernate;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class QueryStudentDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {			
			// start a transaction
						session.beginTransaction();
						
						//query Students
						List<Student> theStudents = session.createQuery("from Student").getResultList();
						
						displayStudents(theStudents);
						
						//querying with where clause where lastName="Sharma"
						theStudents = session.createQuery("from Student where lastName='Sharma'").getResultList();
						System.out.println("Where Clause: "+theStudents);
						
						//querying  with OR clause
						theStudents = session.createQuery("from Student s where s.lastName='Doe' OR firstName='Daffy'").getResultList();
						System.out.println("OR clause: "+theStudents);
						
						//querying with LIKE clause
						theStudents = session.createQuery("from Student s where s.firstName LIKE '%fy'").getResultList();
						System.out.println("LIKE clause: "+theStudents);
						
						// commit transaction
						session.getTransaction().commit();
						
						System.out.println("Done!");
		}
		finally {
			factory.close();
		}
	}

	private static void displayStudents(List<Student> theStudents) {
		for(Student tempStudent: theStudents) {
			System.out.println(tempStudent);
		}
	}
}
