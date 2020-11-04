package gleb.repositories;

import gleb.Config.hibernate.HibernateUtils;
import org.hibernate.Session;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Configuration
public class AbstractRepositoryConfiguration {

  private Session hibernateSession;

  @Bean
  public Session getHibernateSession() {
    return hibernateSession;
  }

  @PostConstruct
  public void initHibernateSession() {
    try {
      hibernateSession = HibernateUtils.getSessionFactory().openSession();
    } catch (Exception e) {
//      logger.log(e);
    }
  }

  @PreDestroy
  public void destroyHibernateSession() {
    try {
      HibernateUtils.shutdown();
    } catch (Exception e) {
//      logger.log(e);
    }
  }
}
