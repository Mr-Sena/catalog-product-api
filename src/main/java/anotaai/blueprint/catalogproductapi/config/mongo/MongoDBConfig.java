package anotaai.blueprint.catalogproductapi.config.mongo;

import com.mongodb.client.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
public class MongoDBConfig {


    @Bean
    public MongoDatabaseFactory mongoConfigure() {


        return new SimpleMongoClientDatabaseFactory(
                "mongodb://mongouser:mongopwd@localhost:27017/product-catalog-data?authSource=admin");
    }

    @Bean
    public MongoTemplate mongoTemplate() {

        return new MongoTemplate(mongoConfigure());
    }
}
