package entitymanager;

import org.jpwh.model.associations.onetomany.cascadepersist.Bid;
import org.jpwh.model.associations.onetomany.cascadepersist.Item;
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
public class OneToManyCascadePersistEntityManagerTest {
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;

	@BeforeClass
	public static void setUpEntityManager() {
		entityManagerFactory = Persistence.createEntityManagerFactory("OneToManyCascadePersistPU");
		entityManager = entityManagerFactory.createEntityManager();
	}

	@AfterClass
	public static void closeAll() {
		entityManager.close();
		entityManagerFactory.close();
	}

	@Test
	public void createCascadePersistOneToMany() {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Item item = new Item();
		item.setName("Some cascade persist item from EM");
		entityManager.persist(item);

		Bid firstBid = new Bid();
		firstBid.setValue(new BigDecimal("12345.00"));
		firstBid.setItem(item);
		item.getBids().add(firstBid);

//		entityManager.persist(firstBid);   НЕ НУЖНО, т.к. cascade = CascadeType.PERSIST: сохраняются все связанные объекты Bid

		Bid secondBid = new Bid();
		secondBid.setValue(new BigDecimal("54321.00"));
		secondBid.setItem(item);
		item.getBids().add(secondBid);

//		entityManager.persist(secondBid); НЕ НУЖНО, т.к. cascade = CascadeType.PERSIST: сохраняются все связанные объекты Bid
		transaction.commit();
	}

	@Test
	public void cascadeDelete() {
		long itemIdForDelete = 3L;
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Item itemForCascadeDelete = entityManager.find(Item.class, itemIdForDelete);
		entityManager.remove(itemForCascadeDelete);
		transaction.commit();
	}
}
