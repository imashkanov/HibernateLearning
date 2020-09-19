import org.jpwh.model.helloworld.Message;
import org.junit.*;
import org.junit.runners.MethodSorters;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.metamodel.ManagedType;
import javax.persistence.metamodel.Metamodel;
import javax.persistence.metamodel.SingularAttribute;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EntityManagerTest {

  private static EntityManagerFactory entityManagerFactory;
  private static EntityManager entityManager;

  @BeforeClass
  public static void setUpEntityManager() {
    entityManagerFactory = Persistence.createEntityManagerFactory("HelloWorldPU");
    entityManager = entityManagerFactory.createEntityManager();
  }

  @AfterClass
  public static void closeAll() {
    entityManager.close();
    entityManagerFactory.close();
  }

  @Test
  public void m01_createMessages() {
    EntityTransaction transaction = entityManager.getTransaction();
    List<Message> messages = createMessages(5);
    transaction.begin();
    messages.forEach(message -> entityManager.persist(message));
    transaction.commit();
  }

  @Test
  public void m02_getCreatedMessages() {
    List<Message> messages = getCurrentMessages();
    System.out.printf("Created messages: %s\n\n", messages);
  }

  @Test
  public void m03_updateMessages() {
    List<Message> messages = getCurrentMessages();
    EntityTransaction transaction = entityManager.getTransaction();
    transaction.begin();
    for (int i=0; i< messages.size(); i++) {
      if ((i+1) % 2 == 0) {
        messages.get(i).setText("It's even. Changed by EM message #" + String.valueOf(i+1));
      } else {
        messages.get(i).setText("It's odd. Changed by EM message #" + String.valueOf(i+1));
      }
    }
    transaction.commit();
  }

  @Test
  public void m04_getUpdatesMessages() {
    List<Message> messages = getCurrentMessages();
    System.out.printf("Updated messages: %s\n", messages);
  }

  @Test
  public void m05_deleteMessages() {
    List<Message> messages = getCurrentMessages();
    if (messages.size() > 0) {
      EntityTransaction transaction = entityManager.getTransaction();
      transaction.begin();
      messages.forEach(message -> entityManager.remove(message));
      transaction.commit();
    }
    System.out.printf("Messages have been deleted. Count of messages=: %d\n", getCurrentMessages().size());
  }

  @Test
  public void m06_dynamicMetaModelTest() {
    Metamodel metaModel = entityManagerFactory.getMetamodel();
    Set<ManagedType<?>> managedTypes = metaModel.getManagedTypes();
    for (ManagedType managedType: managedTypes) {
      System.out.printf("Managed type: class = [%s],  type = [%s]\n", managedType.getJavaType().getName(), managedType.getPersistenceType());
      if (managedType.getJavaType().getName().equals("org.jpwh.model.simple.Item")) {
        processItemAttributesCheckingDyn(managedType);
      }
    }
  }

  private List<Message> createMessages(int count) {
    List<Message> result = new ArrayList<>();
    for (int i=0; i<count; i++) {
      Message message = new Message();
      message.setText(String.format("Created message by EM #%d", i+1));
      result.add(message);
    }
    return result;
  }

  private List<Message> getCurrentMessages() {
    return (List<Message>) entityManager.createQuery("select m from Message m").getResultList();
  }

  private void processItemAttributesCheckingDyn(ManagedType itemType) {
    SingularAttribute nameAttr = itemType.getSingularAttribute("name");
    assertEquals(String.class, nameAttr.getJavaType());
    assertFalse(nameAttr.isOptional());

    SingularAttribute auctionEndAttr = itemType.getSingularAttribute("auctionEnd");
    assertEquals(Date.class, auctionEndAttr.getJavaType());
    assertFalse(auctionEndAttr.isCollection());
    assertFalse(auctionEndAttr.isId());
  }
}
