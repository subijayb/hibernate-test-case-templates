package org.hibernate.bugs;

import static org.junit.Assert.assertEquals;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.sqm.internal.QuerySqmImpl;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Persistence;
import jakarta.persistence.Table;

/**
 * This template demonstrates how to develop a test case for Hibernate ORM, using the Java Persistence API.
 */
public class JPAUnitTestCase {

	private EntityManagerFactory entityManagerFactory;

	@Before
	public void init() {
		entityManagerFactory = Persistence.createEntityManagerFactory( "templatePU" );
	}

	@After
	public void destroy() {
		entityManagerFactory.close();
	}

	// Entities are auto-discovered, so just add them anywhere on class-path
	// Add your tests, using standard JUnit.
	//@Test
	@Ignore
	public void hhh123Test() throws Exception {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		// entityManager.getTransaction().begin();
		// Do stuff...
		// entityManager.getTransaction().commit();
		// entityManager.close();

		MyEntity entity1 = new MyEntity("N1");
		MyEntity entity2 = new MyEntity("N2");
		entityManager.persist(entity1);
		entityManager.persist(entity2);

		QuerySqmImpl q = (QuerySqmImpl)entityManager.createNamedQuery("deleteByName");
		q.setParameter("name", "N1");
		int d = q.executeUpdate();
		assertEquals(1, d);
		System.out.println("deleted count = " + d);
		entityManager.close();
	}
	
	//@Test // If you want to run this, uncomment hibernate.hbm2ddl.auto property in persistence.xml
	public void smpMyEntitytest() throws Exception {
		System.out.println("RUNNING SMPTEST");
		EntityManager entityManager = entityManagerFactory.createEntityManager();		
		Session session = entityManager.unwrap(Session.class);		

		session.getTransaction().begin();
		
		MyEntity entity1 = new MyEntity("SMP");
		session.persist(entity1);

		//Query q = session.createQuery("update MyEntity set name=:updatedname where name=:name", null);
		MutationQuery q = session.createMutationQuery("update MyEntity set name=:updatedname where name=:name");
		q.setParameter("name", "SMP");
		q.setParameter("updatedname", "SMP-ipdated");

		int d = q.executeUpdate();
		session.getTransaction().commit();
		System.out.println("smptest deleted count = " + d);
		
		
		session.close();
		entityManager.close();
	}
	
	@Entity(name = "MyEntity")
	@Table(name = "MY_ENTITY")
	@NamedQuery(name = "deleteByName", query = "delete from MyEntity where name = :name")
	public static class MyEntity {
		@Id
		@GeneratedValue
		long id;

		String name;

		public MyEntity() {
		}

		public MyEntity(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
	}
	
}
