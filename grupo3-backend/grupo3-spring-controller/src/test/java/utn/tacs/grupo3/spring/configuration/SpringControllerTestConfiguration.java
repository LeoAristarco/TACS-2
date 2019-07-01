package utn.tacs.grupo3.spring.configuration;

import java.time.LocalDate;
import java.time.Month;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;

import utn.tacs.grupo3.service.helper.TodayHelper;

@Configuration
@ComponentScan(basePackages = "utn.tacs.grupo3")
public class SpringControllerTestConfiguration {
	
	@Bean
	public TodayHelper todayHelper() {
		return () -> LocalDate.of(2019, Month.APRIL, 1);
	}
	
	@Bean
	public MongoClient mongoClient() {
		return new MongoClient("localhost", 27027);
	}
	
	@Bean
	public MongoOperations mongoOps() {
		return new MongoTemplate(mongoClient(), "grupo3-mongodb-test");
	}
}
