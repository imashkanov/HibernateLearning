package entitymanager;

import org.jpwh.model.associations.manytomany.ternary.CategorizedItem;
import org.jpwh.model.associations.manytomany.ternary.Category;
import org.jpwh.model.associations.manytomany.ternary.Item;
import org.jpwh.model.associations.manytomany.ternary.User;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ManyToManyTernaryTest {
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;

	@BeforeClass
	public static void setUpEntityManager() {
		entityManagerFactory = Persistence.createEntityManagerFactory("ManyToManyTernaryPU");
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

		User someUser = new User("johndoe");
		entityManager.persist(someUser);

		CategorizedItem linkOne = new CategorizedItem(someItem, someUser);
		someCategory.getCategorizedItems().add(linkOne);

		CategorizedItem linkTwo = new CategorizedItem(otherItem, someUser);
		someCategory.getCategorizedItems().add(linkTwo);

		CategorizedItem linkThree = new CategorizedItem(someItem, someUser);
		otherCategory.getCategorizedItems().add(linkThree);

		transaction.commit();
	}

	@Test
	public void testGet() {
		long itemID = 1L;
		Item item = entityManager.find(Item.class, itemID);
		List<Category> categoriesOfItem = entityManager.createQuery("select c from org.jpwh.model.associations.manytomany.ternary.Category c " +
			"join c.categorizedItems ci where ci.item = :itemParam")
			.setParameter("itemParam", item).getResultList();
		System.out.println("DONE");
	}
}
