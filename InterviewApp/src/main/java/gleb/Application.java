package gleb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//@Configuration
//@EnableAutoConfiguration
//@ComponentScan
@SpringBootApplication
public class Application {

//	private static Log logger = LogFactory.getLog(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

//	@Bean
//	protected ServletContextListener listener() {
//		return new ServletContextListener() {
//			@Override
//			public void contextInitialized(ServletContextEvent sce) {
//				logger.info("ServletContext initialized");
//			}
//
//			@Override
//			public void contextDestroyed(ServletContextEvent sce) {
//				logger.info("ServletContext destroyed");
//			}
//		};
//	}

}
