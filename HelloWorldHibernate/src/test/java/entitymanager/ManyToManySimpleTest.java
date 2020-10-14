package entitymanager;

import org.jpwh.model.associations.manytomany.bidirectional.Category;
import org.jpwh.model.associations.manytomany.bidirectional.Item;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ManyToManySimpleTest {
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;

	@BeforeClass
	public static void setUpEntityManager() {
		entityManagerFactory = Persistence.createEntityManagerFactory("ManyToManySimplePU");
		entityManager = entityManagerFactory.createEntityManager();
	}

	@AfterClass
	public static void closeAll() {
		entityManager.close();
		entityManagerFactory.close();
	}

	@Test
	public void testCreateManyToMany() {
		Category someCategory = new Category("Some Category");
		Category otherCategory = new Category("Other Category");

		Item someItem = new Item("Some Item");
		Item otherItem = new Item("Other Item");

		someCategory.getItems().add(someItem);
		someItem.getCategories().add(someCategory);

		someCategory.getItems().add(otherItem);
		otherItem.getCategories().add(someCategory);

		otherCategory.getItems().add(someItem);
		someItem.getCategories().add(otherCategory);

		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(someCategory);
		entityManager.persist(otherCategory);
		transaction.commit();
	}
}
