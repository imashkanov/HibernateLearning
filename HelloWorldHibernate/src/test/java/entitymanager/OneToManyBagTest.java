package entitymanager;

import org.jpwh.model.associations.onetomany.bag.Bid;
import org.jpwh.model.associations.onetomany.bag.Item;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class OneToManyBagTest {

	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;

	@BeforeClass
	public static void setUpEntityManager() {
		entityManagerFactory = Persistence.createEntityManagerFactory("OneToManyBagPU");
		entityManager = entityManagerFactory.createEntityManager();
	}

	@AfterClass
	public static void closeAll() {
		entityManager.close();
		entityManagerFactory.close();
	}

	@Test
	public void testCreate() {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Item someItem = new Item("some item");
		entityManager.persist(someItem);
		Bid someBid = new Bid(new BigDecimal("123.00"), someItem);
		someItem.getBids().add(someBid);
		someItem.getBids().add(someBid); //не вызывает немедленного сохранения!
		entityManager.persist(someBid);
		transaction.commit();
		assertEquals(2, someItem.getBids().size());
	}

	@Test
	public void testGet() {
		Item item = entityManager.find(Item.class, 3L);
		assertEquals(1, item.getBids().size());
	}
}
