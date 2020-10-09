package entitymanager;

import org.jpwh.model.associations.onetomany.orphanremoval.Bid;
import org.jpwh.model.associations.onetomany.orphanremoval.Item;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OneToManyOrphanRemovalEntityManagerTest {
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;

	@BeforeClass
	public static void setUpEntityManager() {
		entityManagerFactory = Persistence.createEntityManagerFactory("OneToManyOrphanRemovalPU");
		entityManager = entityManagerFactory.createEntityManager();
	}

	@AfterClass
	public static void closeAll() {
		entityManager.close();
		entityManagerFactory.close();
	}

	@Test
	public void orphanRemovalDelete() {
		long itemIdForDelete = 5L;
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Item item = entityManager.find(Item.class, itemIdForDelete);
		Bid firstBid = item.getBids().iterator().next();
		item.getBids().remove(firstBid);
		transaction.commit();
	}
}
