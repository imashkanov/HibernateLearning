import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.jpwh.helloworld.HibernateUtils;
import org.jpwh.model.helloworld.Message;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SessionTest {
  private static SessionFactory sessionFactory;
  private static Session session;

  @BeforeClass
  public static void setUpSession() {
    sessionFactory = HibernateUtils.getSessionFactory();
    session = sessionFactory.openSession();
  }

  @AfterClass
  public static void closeAll() {
    session.close();
    HibernateUtils.shutdown();
  }

  @Test
  public void m01_createMessagesWithPersist() {
    Transaction transaction = session.getTransaction();
    transaction.begin();
    createMessages(5).forEach(message -> session.persist(message));
    transaction.commit();
  }

  @Test
  public void m01_createMessagesWithSave() {
    Transaction transaction = session.getTransaction();
    transaction.begin();
    createMessages(5).forEach(message -> session.save(message));
    transaction.commit();
  }

  @Test
  public void m01_createMessagesWithSaveOrUpdate() {
    Transaction transaction = session.getTransaction();
    transaction.begin();
    createMessages(5).forEach(message -> session.saveOrUpdate(message));
    transaction.commit();
  }

  @Test
  public void m02_getCreatedMessages() {
    List<Message> messages = getCurrentMessages();
    System.out.printf("Created messages: %s\n\n", messages);
  }

  @Test // детектит автоматически, что состояние объектов изменилось
  public void m03_updateMessages() {
    List<Message> messages = getCurrentMessages();
    Transaction transaction = session.getTransaction();
    transaction.begin();
    for (int i=0; i< messages.size(); i++) {
      if ((i+1) % 2 == 0) {
        messages.get(i).setText("It's even. Changed by Session message #" + String.valueOf(i+1));
      } else {
        messages.get(i).setText("It's odd. Changed by Session message #" + String.valueOf(i+1));
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
      Transaction transaction = session.getTransaction();
      transaction.begin();
      messages.forEach(message -> session.delete(message));
      transaction.commit();
    }
    System.out.printf("Messages have been deleted. Count of messages = %d.\n", getCurrentMessages().size());
  }

  private List<Message> getCurrentMessages() {
    return session.createCriteria(Message.class).list();
  }

  private List<Message> createMessages(int count) {
    List<Message> result = new ArrayList<>();
    for (int i=0; i<count; i++) {
      Message message = new Message();
      message.setText(String.format("Created message by Session #%d", i+1));
      result.add(message);
    }
    return result;
  }
}
