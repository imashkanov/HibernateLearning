package entitymanager;

import org.jpwh.model.associations.manytomany.ternary.map.Category;
import org.jpwh.model.associations.manytomany.ternary.map.Item;
import org.jpwh.model.associations.manytomany.ternary.map.User;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ManyToManyTernaryMapTest {
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;

	@BeforeClass
	public static void setUpEntityManager() {
		entityManagerFactory = Persistence.createEntityManagerFactory("ManyToManyTernaryMapPU");
		entityManager = entityManagerFactory.createEntityManager();
	}

	@AfterClass
	public static void closeAll() {
		entityManager.close();
		entityManagerFactory.close();
	}

	@Test
	public void testManyToManyTernaryMapCreate() {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Category someCategory = new Category("Some Category");
		Category otherCategory = new Category("Other Category");

		entityManager.persist(someCategory);
		entityManager.persist(otherCategory);

		Item someItem = new Item("Some Item");
		Item otherItem = new Item("Other Item");
		entityManager.persist(someItem);
		entityManager.persist(otherItem);

		User someUser = new User("johndoe");
		entityManager.persist(someUser);

		someCategory.getItemAddedBy().put(someItem, someUser);
		someCategory.getItemAddedBy().put(otherItem, someUser);
		otherCategory.getItemAddedBy().put(someItem, someUser);

		transaction.commit();
	}
}
