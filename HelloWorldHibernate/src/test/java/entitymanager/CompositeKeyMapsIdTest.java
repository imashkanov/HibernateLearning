package entitymanager;

import org.jpwh.model.complexschemas.compositekey.mapsid.Department;
import org.jpwh.model.complexschemas.compositekey.mapsid.User;
import org.jpwh.model.complexschemas.compositekey.mapsid.UserId;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class CompositeKeyMapsIdTest {
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;

	@BeforeClass
	public static void setUpEntityManager() {
		entityManagerFactory = Persistence.createEntityManagerFactory("CompositeKeyMapsIdPU");
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
		Department department = new Department("test department");
		entityManager.persist(department);
		UserId userId = new UserId("testUser", 232311231L); //это значение проигнорируется, так как MapId
		User user = new User(userId);
		user.setDepartment(department);
		entityManager.persist(user);
		transaction.commit();
	}

	@Test
	public void testGet() {
		UserId userId = new UserId("testUser", 1L);
		User user = entityManager.find(User.class, userId);
		System.out.println(user);
	}
}
