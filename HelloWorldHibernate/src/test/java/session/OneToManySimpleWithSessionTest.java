package session;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.jpwh.helloworld.HibernateUtils;
import org.jpwh.model.associations.onetomany.bidirectional.Bid;
import org.jpwh.model.associations.onetomany.bidirectional.Item;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.math.BigDecimal;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OneToManySimpleWithSessionTest {
	private static SessionFactory sessionFactory;
	private static Session session;

	@BeforeClass
	public static void setUpSession() {
		sessionFactory = HibernateUtils.getSessionFactory();
		session = sessionFactory.openSession();
	}

	@AfterClass
	public static void closeAll() {
		session.close();
		HibernateUtils.shutdown();
	}

	@Test
	public void createOneToMany() {
		Transaction transaction = session.beginTransaction();
		Item item = new Item();
		item.setName("Some item from session 1");
		session.persist(item);

		Bid firstBid = new Bid();
		firstBid.setValue(new BigDecimal("123.00"));
		firstBid.setItem(item);
		item.getBids().add(firstBid);

		session.persist(firstBid);

		Bid secondBid = new Bid();
		secondBid.setValue(new BigDecimal("456.00"));
		secondBid.setItem(item);
		item.getBids().add(secondBid);

		session.persist(secondBid);
		transaction.commit();
	}
}
