package entitymanager;

import org.hibernate.Transaction;
import org.jpwh.model.associations.onetomany.bidirectional.Bid;
import org.jpwh.model.associations.onetomany.bidirectional.Item;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.math.BigDecimal;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OneToManySimpleEntityManagerTest {
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;

	@BeforeClass
	public static void setUpEntityManager() {
		entityManagerFactory = Persistence.createEntityManagerFactory("OneToManySimplePU");
		entityManager = entityManagerFactory.createEntityManager();
	}

	@AfterClass
	public static void closeAll() {
		entityManager.close();
		entityManagerFactory.close();
	}

	@Test
	public void createSimpleOneToMany() {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Item item = new Item();
		item.setName("Some item from EM");
		entityManager.persist(item);

		Bid firstBid = new Bid();
		firstBid.setValue(new BigDecimal("112233.00"));
		firstBid.setItem(item);
		item.getBids().add(firstBid);

		entityManager.persist(firstBid);

		Bid secondBid = new Bid();
		secondBid.setValue(new BigDecimal("445566.00"));
		secondBid.setItem(item);
		item.getBids().add(secondBid);

		entityManager.persist(secondBid);
		transaction.commit();
	}
}
