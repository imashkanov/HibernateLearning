package entitymanager;

import org.jpwh.model.complexschemas.compositekey.readonly.Department;
import org.jpwh.model.complexschemas.compositekey.readonly.User;
import org.jpwh.model.complexschemas.compositekey.readonly.UserId;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

public class CompositeKeyReadonlyTest {
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;

	@BeforeClass
	public static void setUpEntityManager() {
		entityManagerFactory = Persistence.createEntityManagerFactory("CompositeKeyReadonlyPU");
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
		UserId id = new UserId("test user", department.getId());
		User user = new User(id);
		entityManager.persist(user);
		transaction.commit();
		assertNull(user.getDepartment()); //тут будет Null, так как он заполнится только при загрузке
	}

	@Test
	public void testGet() {
		UserId userId = new UserId("test user", 1L);
		User user = entityManager.find(User.class, userId);
		assertEquals("test department", user.getDepartment().getName());
		System.out.println(user);
	}
}
