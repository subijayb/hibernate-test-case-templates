package org.hibernate.bugs;

import java.sql.SQLException;

import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * This template demonstrates how to develop a test case for Hibernate ORM,
 * using the Java Persistence API.
 */
public class SMPJPAUnitTestCase {

	private EntityManagerFactory entityManagerFactory;

	@Before
	public void init() throws SQLException {
		entityManagerFactory = Persistence.createEntityManagerFactory("templatePU");
	}

	@After
	public void destroy() {
		 //entityManagerFactory.close();
	}

	@Test
	public void smpTest() throws Exception {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Session session = entityManager.unwrap(Session.class);
		session.getTransaction().begin();
		Subscriber s1 = new Subscriber();
		s1.setId((long) 1);
		s1.setAccountNumber("account1");
		s1.setValue("value1");
		//s1.setUservalue("value1");
		//s1.setUservalue_clob("value2222221");
		session.save(s1);
		
//		org.hibernate.query.Query<Subscriber> cq = session.createQuery("from test_subscriber ", Subscriber.class);
//		List<Subscriber> cqresult = cq.getResultList();
//		System.out.println("result cq======" + cqresult);
//		System.out.println("result cq:0th element======subscriberid=" + cqresult.get(0).getAccountNumber());

	}

}
