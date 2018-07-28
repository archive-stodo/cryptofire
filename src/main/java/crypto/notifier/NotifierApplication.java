package crypto.notifier;

import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.sql.DataSource;

@SpringBootApplication
public class NotifierApplication {

	public static void main(String[] args) {

		SpringApplication.run(NotifierApplication.class, args);
//		Flyway flyway = new Flyway();
//		flyway.setDataSource("jdbc:h2:mem:testdb", "sa", null);
//		flyway.migrate();
	}
}
