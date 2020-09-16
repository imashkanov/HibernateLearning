package org.jpwh.helloworld;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.jpwh.model.helloworld.Message;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    //processWithEntityManager();
    processWithSession();
  }

  private static void processWithSession() {
    SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
    Session session = sessionFactory.openSession();
    createMessagesBySession(session);
    List<Message> messages = getCreatedBySessionMessages(session);
    System.out.printf("Messages: %s\n", messages);
    session.close();
    sessionFactory.close();
  }

  private static void processWithEntityManager() {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("HelloWorldPU");
    EntityManager em = emf.createEntityManager();
    createMessagesByEM(em);
    List<Message> messages = getCreatedByEmMesages(em);
    System.out.printf("Messages: %s\n", messages);
    updateMessageByEM(em, messages.get(0), "Changed text 1 with em");
    System.out.printf("Updated first element. Messages after update: %s\n", messages);
    updateMessageByEM(em, messages.get(1), "Changed text 2 with em");
    System.out.printf("Updated second element. Messages after update: %s\n", messages);
    em.close();
  }

  private static List<Message> getCreatedBySessionMessages(Session session) {
    return session.createCriteria(Message.class).list();
  }

  private static void updateMessageByEM(EntityManager em, Message message, String newText) {
    EntityTransaction transaction = em.getTransaction();
    transaction.begin();
    message.setText(newText);
    transaction.commit();
  }

  private static List<Message> getCreatedByEmMesages(EntityManager em) {
    return (List<Message>) em.createQuery("select m from Message m").getResultList();
  }

  private static void createMessagesBySession(Session session) {
    Transaction transaction = session.getTransaction();
    transaction.begin();
    Message message1 = new Message();
    message1.setText("testMessageWithSession1");
    Message message2 = new Message();
    message2.setText("testMessageWithSession2");
    session.persist(message1);
    session.persist(message2);
    transaction.commit();
  }

  private static void createMessagesByEM(EntityManager em) {
    Message message1 = new Message();
    message1.setText("testMessageWithEM1");
    Message message2 = new Message();
    message2.setText("testMessageWithEM2");
    EntityTransaction transaction = em.getTransaction();
    transaction.begin();
    em.persist(message1);
    em.persist(message2);
    transaction.commit();
  }
}
