package session;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.jpwh.helloworld.HibernateUtils;
import org.jpwh.model.associations.onetomany.cascadepersist.Bid;
import org.jpwh.model.associations.onetomany.cascadepersist.Item;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.persistence.EntityTransaction;
import java.math.BigDecimal;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OneToManyCascadePersistSessionTest {
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
	public void createCascadePersistOneToMany() {
		Transaction transaction = session.beginTransaction();
		Item item = new Item();
		item.setName("Some cascade persist item from Session");

		Bid firstBid = new Bid();
		firstBid.setValue(new BigDecimal("112233.00"));
		firstBid.setItem(item);
		item.getBids().add(firstBid);

//		session.persist(firstBid);   НЕ НУЖНО, т.к. cascade = CascadeType.PERSIST: сохраняются все связанные объекты Bid

		Bid secondBid = new Bid();
		secondBid.setValue(new BigDecimal("332211.00"));
		secondBid.setItem(item);
		item.getBids().add(secondBid);

//		session.persist(secondBid); НЕ НУЖНО, т.к. cascade = CascadeType.PERSIST: сохраняются все связанные объекты Bid
		session.persist(item);
		transaction.commit();
	}

	@Test
	public void cascadeDelete() {
		long itemIdForDelete = 4L;
		Transaction transaction = session.beginTransaction();
		Item itemForDelete = session.get(Item.class, itemIdForDelete);
		session.delete(itemForDelete);
		transaction.commit();
	}
}
