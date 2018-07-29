package crypto.notifier;

import crypto.notifier.binance.restcontroller.SymbolController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"crypto.notifier.binance"})
//@EnableJpaRepositories(basePackages = {"crypto.notifier.binance.repository"})
public class NotifierApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotifierApplication.class, args);
	}
}
