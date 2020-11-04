package gleb;

import gleb.Config.hibernate.HibernateUtils;
import org.hibernate.Session;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

//@Configuration
//@EnableAutoConfiguration
//@ComponentScan
@SpringBootApplication
public class Application {

  private Session hibernateSession;

//	private static Log logger = LogFactory.getLog(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	protected ServletContextListener listener() {
		return new ServletContextListener() {
			@Override
			public void contextInitialized(ServletContextEvent sce) {
        System.out.println("ServletContext initialized");
			}

			@Override
			public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("ServletContext destroyed");
			}
		};
	}

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
