package org.hibernate.bugs;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;

/**
 * This template demonstrates how to develop a standalone test case for Hibernate ORM.  Although this is perfectly
 * acceptable as a reproducer, usage of ORMUnitTestCase is preferred!
 */
public class ORMStandaloneTestCase {

	private SessionFactory sf;

	//@Before
	public void setup() {
		StandardServiceRegistryBuilder srb = new StandardServiceRegistryBuilder()
			// Add in any settings that are specific to your test. See resources/hibernate.properties for the defaults.
			.applySetting( "hibernate.show_sql", "true" )
			.applySetting( "hibernate.format_sql", "true" )
			.applySetting( "hibernate.hbm2ddl.auto", "update" );

		Metadata metadata = new MetadataSources( srb.build() )
		// Add your entities here.
		//	.addAnnotatedClass( Foo.class )
			.buildMetadata();

		sf = metadata.buildSessionFactory();
	}
	
	@Before
	public void setupConfig() {
		Configuration config = new Configuration();
		sf = config.configure().buildSessionFactory();
	}

	// Add your tests, using standard JUnit.

	@Test
	public void hhh123Test() throws Exception {
		Session session = sf.openSession();
		
		org.hibernate.query.Query<Subscriber> cq = session.createQuery("from Subscriber ", Subscriber.class);
		List<Subscriber> cqresult = cq.getResultList();
		System.out.println("result cq======" + cqresult);
		System.out.println("result cq:0th element======subscriberid=" + cqresult.get(0).getAccountNumber());
		
		session.close();
		sf.close();

	}
}
