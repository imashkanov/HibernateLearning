package gleb.repositories.config;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactoryConfigurator {
  private static EntityManagerFactory entityManagerFactory = buildEntityManagerFactory();

  private static EntityManagerFactory buildEntityManagerFactory() {
    return Persistence.createEntityManagerFactory("InterviewAppPU");
  }

  public static EntityManagerFactory getEntityManagerFactory() {
    return entityManagerFactory;
  }

  public void shutDowm() {
    getEntityManagerFactory().close();
  }
}
