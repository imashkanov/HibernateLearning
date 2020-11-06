package gleb.repositories.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;

@Configuration
public class EntityManagerConfiguration {

  private EntityManager entityManager;

  @Bean
  public EntityManager getEntityManager() {
    return entityManager;
  }

  @PostConstruct
  private void initEntityManager() {
    entityManager = EntityManagerFactoryConfigurator.getEntityManagerFactory().createEntityManager();
  }

  @PreDestroy
  private void destroyEntityManager() {
    entityManager.close();
  }

}
