package crypto.notifier;

import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"crypto.notifier.binance"} )
@EnableJpaRepositories(basePackages = {"crypto.notifier.binance.repository"})
public class NotifierApplication {

	public static void main(String[] args) {

		Flyway flyway = new Flyway();
		flyway.setDataSource("jdbc:postgresql://localhost:5432/notifierdb", "postgres", "admin123");
		flyway.setInitOnMigrate(true);
		flyway.migrate();

		SpringApplication.run(NotifierApplication.class, args);
	}
}
