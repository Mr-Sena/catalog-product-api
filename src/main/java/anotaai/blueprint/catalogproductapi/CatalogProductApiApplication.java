package anotaai.blueprint.catalogproductapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
@SpringBootApplication
public class CatalogProductApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatalogProductApiApplication.class, args);
	}

}
