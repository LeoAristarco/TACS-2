package utn.tacs.grupo3.mongo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;

@Configuration
@ComponentScan(basePackages = "utn.tacs.grupo3.repository.mongo")
public class MongoConfiguration {
	
	@Bean
	public MongoClient mongoClient() {
		return new MongoClient("localhost");
	}
	
	@Bean
	public MongoOperations mongoOps() {
		return new MongoTemplate(mongoClient(), "grupo3-mongodb");
	}

}
