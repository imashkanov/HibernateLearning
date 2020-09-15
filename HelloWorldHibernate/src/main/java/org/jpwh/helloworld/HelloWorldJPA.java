package org.jpwh.helloworld;

import org.jpwh.model.helloworld.Message;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class HelloWorldJPA {

  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("HelloWorldPU");
    EntityManager em = emf.createEntityManager();
    Message message1 = new Message();
    message1.setText("testMessage1");
    Message message2 = new Message();
    message2.setText("testMessage2");
    EntityTransaction transaction = em.getTransaction();
    transaction.begin();
    em.persist(message1);
    em.persist(message2);
    transaction.commit();
    em.close();
  }
}
