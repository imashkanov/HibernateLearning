package entitymanager;

import org.jpwh.model.associations.onetoone.jointable.Item;
import org.jpwh.model.associations.onetoone.jointable.Shipment;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class OneToOneJoinTableTest {

	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;

	@BeforeClass
	public static void setUpEntityManager() {
		entityManagerFactory = Persistence.createEntityManagerFactory("OneToOneJoinTablePU");
		entityManager = entityManagerFactory.createEntityManager();
	}

	@AfterClass
	public static void closeAll() {
		entityManager.close();
		entityManagerFactory.close();
	}

	@Test
	public void create() {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Shipment someShipment = new Shipment();
		entityManager.persist(someShipment);
		Item item = new Item("Join Table Item");
		entityManager.persist(item);
		Shipment joinTableShipment = new Shipment(item);
		entityManager.persist(joinTableShipment);
		transaction.commit();
	}

}
