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
		MongoClientURI uri = new MongoClientURI(
			    "mongodb+srv://grupo3-mongo:tacs-utn@cluster0-wgdgu.mongodb.net/test?retryWrites=true&w=majority");
		return new MongoClient(uri);
	}
	
	@Bean
	public MongoOperations mongoOps() {
		return new MongoTemplate(mongoClient(), "grupo3-mongodb");
	}

}
