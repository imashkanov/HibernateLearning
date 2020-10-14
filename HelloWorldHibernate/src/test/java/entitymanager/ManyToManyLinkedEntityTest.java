package entitymanager;

import org.jpwh.model.associations.manytomany.linkentity.CategorizedItem;
import org.jpwh.model.associations.manytomany.linkentity.Category;
import org.jpwh.model.associations.manytomany.linkentity.Item;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ManyToManyLinkedEntityTest {
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;

	@BeforeClass
	public static void setUpEntityManager() {
		entityManagerFactory = Persistence.createEntityManagerFactory("ManyToManyLinkedEntityPU");
		entityManager = entityManagerFactory.createEntityManager();
	}

	@AfterClass
	public static void closeAll() {
		entityManager.close();
		entityManagerFactory.close();
	}

	@Test
	public void testManyToManyLinkedEntityCreate() {
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

		CategorizedItem linkOne = new CategorizedItem("user1", someCategory, someItem);
		CategorizedItem linkTwo = new CategorizedItem("user1", someCategory, otherItem);
		CategorizedItem linkThree = new CategorizedItem("user1", otherCategory, someItem);

		entityManager.persist(linkOne);
		entityManager.persist(linkTwo);
		entityManager.persist(linkThree);
		transaction.commit();
	}
}
