package entitymanager;

import org.jpwh.model.associations.onetoone.sharedprimarykey.Address;
import org.jpwh.model.associations.onetoone.sharedprimarykey.User;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class OneToOneSharedPKTest {

	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;

	@BeforeClass
	public static void setUpEntityManager() {
		entityManagerFactory = Persistence.createEntityManagerFactory("OneToOneSharedPrimaryKeyPU");
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
		Address someAddress = new Address("Street1", "Zipcode1","City1");
		entityManager.persist(someAddress);
		User someUser = new User(someAddress.getId(), "User1");
		someUser.setShippingAddress(someAddress);
		entityManager.persist(someUser);
		transaction.commit();
	}

	@Test
	public void getUserWithProxyAddress() {
		long id = 1L;
		User user = entityManager.find(User.class, id);
		Address address = user.getShippingAddress();
		System.out.println(address);
	}
}
