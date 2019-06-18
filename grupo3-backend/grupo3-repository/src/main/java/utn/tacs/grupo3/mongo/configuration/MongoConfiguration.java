package utn.tacs.grupo3.mongo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

@Configuration
@ComponentScan(basePackages = "utn.tacs.grupo3.repository.mongo")
public class MongoConfiguration {
	
	@Bean
	public MongoClient mongoClient() {
		MongoClientURI uri = new MongoClientURI(System.getenv("MONGO_DB_URI"));
		return new MongoClient(uri);
	}
	
	@Bean
	public MongoOperations mongoOps() {
		return new MongoTemplate(mongoClient(), System.getenv("MONGO_DB_NAME"));
	}

}
