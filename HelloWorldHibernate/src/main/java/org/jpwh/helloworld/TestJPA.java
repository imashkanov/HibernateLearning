package org.jpwh.helloworld;

import org.jpwh.model.helloworld.Message;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class TestJPA {

  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("HelloWorldPU");
    EntityManager em = emf.createEntityManager();
    createMessages(em);
    List<Message> messages = getCreatedMesages(em);
    System.out.printf("Messages: %s\n", messages);
    updateMessage(em, messages.get(0), "Changed text 1");
    System.out.printf("Updated first element. Messages after update: %s\n", messages);
    updateMessage(em, messages.get(1), "Changed text 2");
    System.out.printf("Updated second element. Messages after update: %s\n", messages);
    em.close();
  }

  private static void updateMessage(EntityManager em, Message message, String newText) {
    EntityTransaction transaction = em.getTransaction();
    transaction.begin();
    message.setText(newText);
    transaction.commit();
  }

  private static List<Message> getCreatedMesages(EntityManager em) {
    return (List<Message>) em.createQuery("select m from Message m").getResultList();
  }

  private static void createMessages(EntityManager em) {
    Message message1 = new Message();
    message1.setText("testMessage1");
    Message message2 = new Message();
    message2.setText("testMessage2");
    EntityTransaction transaction = em.getTransaction();
    transaction.begin();
    em.persist(message1);
    em.persist(message2);
    transaction.commit();
  }
}
