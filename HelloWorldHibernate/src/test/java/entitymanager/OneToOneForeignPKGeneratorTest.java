package entitymanager;

import org.jpwh.model.associations.onetoone.foreigngenerator.Address;
import org.jpwh.model.associations.onetoone.foreigngenerator.User;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class OneToOneForeignPKGeneratorTest {

	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;

	@BeforeClass
	public static void setUpEntityManager() {
		entityManagerFactory = Persistence.createEntityManagerFactory("OneToOneForeignGeneratorPU");
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
		User user = new User("User for foreign address generator");
		Address address = new Address("Street2", "Zipcode2", "City2", user);
		user.setShippingAddress(address);
		entityManager.persist(user);
		transaction.commit();
	}
}
