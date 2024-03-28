package org.hibernate.bugs;


import java.util.List;

import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

/**
 * This template demonstrates how to develop a test case for Hibernate ORM, using the Java Persistence API.
 */
public class SMPJPAUnitTestCase {

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
	@Test
	//@Ignore
	public void smpTest() throws Exception {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Session session = entityManager.unwrap(Session.class);

		org.hibernate.query.Query q = session.createNativeQuery("select count(*) from SMPSESSION", Object.class);
		System.out.println("result======" + q.getResultList());
		System.out.println("result======" + ((Number) q.uniqueResult()).longValue());
		
		Query q2 = session.createNativeQuery("select creationdate, subscriberid from SMPSESSION", Object[].class);
		List<Object[]> results = q2.getResultList();
		System.out.println("result 2======" + results);
		System.out.println("result 2:0th element======creationdate=" + results.get(0)[0] + ", subscriberid=" + results.get(0)[1]);
		
		org.hibernate.query.Query<Subscriber> cq = session.createQuery("from Subscriber ", Subscriber.class);
		List<Subscriber> cqresult = cq.getResultList();
		System.out.println("result cq======" + cqresult);
		System.out.println("result cq:0th element======subscriberid=" + cqresult.get(0).getAccountNumber());

		entityManager.close();
	}
	
	
	
	
	
}
