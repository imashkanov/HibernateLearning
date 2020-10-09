package session;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.jpwh.helloworld.HibernateUtils;
import org.jpwh.model.associations.onetomany.orphanremoval.Bid;
import org.jpwh.model.associations.onetomany.orphanremoval.Item;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.persistence.EntityTransaction;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OneToManyOrphanRemovalSessionTest {
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
	public void orphanRemovalDelete() {
		long itemIdForDelete = 6L;
		Transaction transaction = session.beginTransaction();
		Item item = session.get(Item.class, itemIdForDelete);
		Bid firstBid = item.getBids().iterator().next();
		item.getBids().remove(firstBid);
		transaction.commit();
	}
}
