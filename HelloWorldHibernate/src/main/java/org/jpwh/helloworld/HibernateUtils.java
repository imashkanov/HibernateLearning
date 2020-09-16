package org.jpwh.helloworld;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtils {
  private static SessionFactory sessionFactory = buildSessionFactory();

  public static SessionFactory buildSessionFactory() {
    //SessionFactory - singleton!
    final StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
            .configure("hibernate.cfg.xml") // configures settings from hibernate.cfg.xml
            .build();
    try {
      sessionFactory = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return sessionFactory;
  }

  public static SessionFactory getSessionFactory() {
    return sessionFactory;
  }

  public static void shutdown() {
    // Close caches and connection pools
    getSessionFactory().close();
  }
}

